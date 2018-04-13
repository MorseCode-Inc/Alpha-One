package inc.morsecode.kafka.source;

import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.utils.Bytes;

import java.util.Collection;
import java.util.Properties;

public class RawTap extends Tap<Bytes> {

    private Properties consumerProperties;
    
    public RawTap(Collection<Receiver<Bytes>> handlers, int threads) {
    	super(handlers, threads);
        this.consumerProperties= new Properties();
    }
    
    public Tap<Bytes> init(Properties consumerProperties, Collection<String> topics) {
        this.consumerProperties.putAll(consumerProperties);
        this.consumerProperties.setProperty("key.deserializer", StringDeserializer.class.getName());
        this.consumerProperties.setProperty("value.deserializer", BytesDeserializer.class.getName());
        return super.init(this.consumerProperties, topics);
    }
    
}
