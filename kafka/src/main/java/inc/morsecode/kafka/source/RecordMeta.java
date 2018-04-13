package inc.morsecode.kafka.source;

import kafka.message.MessageAndMetadata;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * Provides an abstraction to hold the metadata available when reading a kafka message.
 * 
 * @author bmorse
 *
 */
// NOTE: this class is visible the package only
class RecordMeta implements Metadata {

    final private int partition;
    final private long offset;
    final private String key;
    final private String topic;
    final private long timestamp;    // kafka8 message metadata doesn't have a timestamp.

    // kafka8 
    public RecordMeta(MessageAndMetadata<String, ?> record) {
        this.partition= record.partition();
        this.offset= record.offset();
        this.key= record.key();
        this.topic= record.topic();
        this.timestamp= System.currentTimeMillis();    // fake it
    }

    // kafka10 
    public RecordMeta(ConsumerRecord<String, ?> record) {
        this.partition= record.partition();
        this.offset= record.offset();
        this.key= record.key();
        this.topic= record.topic();
        this.timestamp= System.currentTimeMillis();    // fake it
    }
    
    @Override
    public String getKey() {
        return key;
    }
    
    @Override
    public long getOffset() {
        return offset;
    }
    
    @Override
    public int getPartition() {
        return partition;
    }
    
    @Override
    public String getTopic() {
        return topic;
    }
    
    @Override
    public long getTimestamp() {
        return timestamp;
    }
}
