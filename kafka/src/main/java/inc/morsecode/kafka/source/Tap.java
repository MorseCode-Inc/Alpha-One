package inc.morsecode.kafka.source;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Simple multi-threaded KafkaConsumer
 */
public class Tap<T> {

    private Map<Worker<T>, KafkaConsumer<String, T>> consumers;
    private List<Receiver<T>> handlers= new ArrayList<>();
    public final int threads;
    
    public Tap(Collection<Receiver<T>> handlers, int threads) {
        this.handlers.addAll(handlers);
        this.threads= threads;
    }
    
    /* (non-Javadoc)
	 * @see com.sovrn.kafka.source.KafkaTap#register(com.sovrn.RecordHandler)
	 */
	public Tap<T> register(Receiver<T> handler) {
        this.handlers.add(handler);
        return this;
    }
    
    /* (non-Javadoc)
	 * @see com.sovrn.kafka.source.KafkaTap#init(java.util.Properties, int, java.lang.String)
	 */
	public Tap<T> init(Properties consumerProperties, String ... topics) {
        return init(consumerProperties, Arrays.asList(topics));
    }

    /* (non-Javadoc)
	 * @see com.sovrn.kafka.source.KafkaTap#init(java.util.Properties, int, java.util.Collection)
	 */
	public Tap<T> init(Properties consumerProperties, Collection<String> topics) {
        if (this.consumers != null) { throw new IllegalStateException("Tap has already been initailized."); }
        this.consumers= new HashMap<>();
        for (int i= 0; i < threads; i++ ) {
            KafkaConsumer<String, T> consumer = new KafkaConsumer<>(consumerProperties);
            consumer.subscribe(topics);
            Worker<T> worker= new Worker<>(this, consumer);
            consumers.put(worker, consumer);
        }
        return this;
    }
    
	public void start() {
        consumers.keySet().forEach(worker -> worker.thread.start());
    }
    
	public void stop() {
        consumers.keySet().forEach(worker -> worker.stop());
    }
    
    private class Worker<V> implements Runnable {
        private boolean running;
        Tap<V> tap;
        Thread thread;
        KafkaConsumer<String, V> consumer;
        public Worker(Tap<V> tap, KafkaConsumer<String, V> consumer) {
            this.tap= tap;
            this.consumer= consumer;
            this.thread= new Thread(this);
        }
        
        public boolean isAlive() {
            return thread.isAlive();
        }
        
        public void stop() { stop(true); }

        public void stop(boolean wait) {
            this.running= false;
            if (wait) {
                try { // wait for thread to recognize stop signal
                    while (isAlive()) {
                        try { Thread.sleep(50); } catch (InterruptedException ignore) { }
                    }
                } catch (Throwable ignore) {}
            }
        }
        
        @Override
        public void run() {
            this.running= true;
            
            try {
                while (running) {
                    try {
                        Iterator<ConsumerRecord<String, V>> iterator = consumer.poll(2000).iterator();
            
                        while (iterator.hasNext()) {
                            ConsumerRecord<String, V> message= iterator.next();
                            tap.handlers.parallelStream().forEach(h -> receive(h, message));
                        }
                    } catch (Throwable error) {
                        tap.handlers.parallelStream().forEach(h -> h.error(null, null, null, error));
                    }
                }
            } finally {
                consumer.close();
                running= false;
            }
        }

        private void receive(Receiver<V> handler, ConsumerRecord<String, V> message) {
            try {
                ((Receiver<V>) handler).handle(new RecordMeta(message), message.key(), message.value());
            } catch (Throwable error) {
                ((Receiver<V>) handler).error(new RecordMeta(message), message.key(), message.value(), error);
            }
        }
    }
    
	public boolean isRunning() {
        for (Worker<T> worker : consumers.keySet()) {
            if (worker.isAlive()) { return true; }
        }
        return false;
    }
}
