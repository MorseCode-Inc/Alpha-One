package inc.morsecode.kafka.source;

public interface Metadata {

	long getTimestamp();

	String getTopic();

	int getPartition();

	long getOffset();

	String getKey();

}
