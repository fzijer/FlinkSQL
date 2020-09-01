import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import scala.Int;

import java.util.Properties;

public class KafkaStreamDemo3 {
    /**
     * 3.完成 一些 DSL中 算子的 使用
     */


    public static void main(String[] args) {
        /**1.基本连接配置*/
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "DSLopreation");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "jiaxun:9092");
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());  //序列化和反序列化
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        /**2.构建 KStreamBuilder  和  KStream,*/
        KStreamBuilder builder = new KStreamBuilder();

        KStream<String, String> source = builder.stream("sourcetopic");         //设定需要消费的主题，得到KStram对象
        KStream mapValuessource = source.mapValues(value -> value.toUpperCase());
        KStream mapsource = source.map(new KeyValueMapper<String, String, KeyValue<String, String>>() {   //第一个参数 key 类型， 第二个参数 value类型， 第三个返回值 类型
            @Override
            public KeyValue<String, String> apply(String key, String value) {
                return new KeyValue(value,value);
            }
        });

         mapsource.print();

        KafkaStreams streams = new KafkaStreams(builder, props);
        streams.start();
    }
}
