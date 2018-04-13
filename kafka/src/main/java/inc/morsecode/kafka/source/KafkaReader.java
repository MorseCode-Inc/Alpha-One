package inc.morsecode.kafka.source;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class KafkaReader<T> {
    
    private BlockingQueue<T> queue= new LinkedBlockingQueue<>();
    private Tap<T> kafka;
    private Listener<T> listener;
    private long maxSize;
    
    private class Listener<V> implements Receiver<V> {
        
        Throwable lastError;
        KafkaReader<V> reader;
        long errors= 0;

        public Listener(KafkaReader<V> reader) {
            this.reader= reader;
        }
        
        @Override
        public void handle(Metadata meta, String key, V record) {
            reader.add(record);
        }
        
        @Override
        public void error(Metadata meta, String key, V record, Throwable error) {
            errors++;
            lastError= error;
        }

    }
    
    public KafkaReader(Tap<T> tap, long maxBufferSize) {
        this.listener= new Listener<>(this);
        this.kafka= tap;
        this.kafka.register(listener);
        if (!tap.isRunning()) {
            tap.start();
        }
    }

    private boolean add(T value) {
        try {
            while (queue.size() >= maxSize) { 
                try { Thread.sleep(14); } catch (InterruptedException ignore) { }
            }
            queue.put(value);
            return true;
        } catch (InterruptedException failed) { }
        return false;
    }

    
    public T read() { return read(5000); }

    public T read(int timeoutMillis) {
        try {
            return queue.poll(timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignore) { }
        return null;
    }
}
