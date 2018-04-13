package inc.morsecode.kafka.process;

/**
 * 
 * Implementations of this interface should be single-use objects.
 * The pattern of use will be:
 * 1. kafka message received by source
 * 2. message is dispatched (by the Dispatcher) to a work queue
 * 3. one or many processors (WorkloadManager) are running and polling this work queue
 * 4. message is taken from the work queue
 * 5. new Thread(new Task<T>(message)).start();
 * 
 */
public interface Task<T> extends Runnable {

    public void run();
    
}
