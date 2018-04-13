package inc.morsecode.kafka.source;

import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collection;
import java.util.Properties;

public class JsonTap<T extends SpecificRecord> extends Tap<T> {

    private DatumReader<T> reader;

    private Properties consumerProperties;
    private String registryUrl;

    public JsonTap(Collection<Receiver<T>> handlers, int threads, String registryUrl) {
        super(handlers, threads);
        this.registryUrl= registryUrl;
        this.consumerProperties= new Properties();
    }

    public Tap<T> init(Properties properties, int threads, String topic, Class serializer) {
        this.consumerProperties.putAll(properties);
        this.consumerProperties.setProperty("key.deserializer", StringDeserializer.class.getName());
        this.consumerProperties.setProperty("value.deserializer", serializer.getName());
        super.init(this.consumerProperties, topic);
        return this;
    }

    
}
