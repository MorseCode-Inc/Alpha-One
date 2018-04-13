package inc.morsecode.kafka.source;

public interface Receiver<T> {
	
	public void handle(Metadata meta, String key, T record);
	
	public void error(Metadata meta, String key, T record, Throwable error);

}
