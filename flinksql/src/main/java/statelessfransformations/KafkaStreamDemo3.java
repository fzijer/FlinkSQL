/*
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import scala.Int;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class KafkaStreamDemo3 {
    */
/**
     * 3.完成  DSL中 一些 算子的 使用
     *//*



    public static void main(String[] args) {
        */
/**1.基本连接配置*//*

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "DSLopreation");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "jiaxun:9092");
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());  //序列化和反序列化
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        */
/**2.构建 KStreamBuilder  和  KStream,*//*

        KStreamBuilder builder = new KStreamBuilder();
        KStream<String, String> source = builder.stream("sourcetopic");         //设定需要消费的主题，得到KStram对象

        KStream mapValuessource = source.mapValues(value -> value.toUpperCase());

        ////第一个参数 key 类型， 第二个参数 value类型， 第三个返回值 类型
        KStream mapsource = source.map(new KeyValueMapper<String, String, KeyValue<String, String>>() {
            @Override
            public KeyValue<String, String> apply(String key, String value) {
                return new KeyValue(value, value);
            }
        });

        KStream filtersource = source.filter((key, value) -> value.contains("name"));       //使用filter 算子

        KStream flatmapsource = source.flatMapValues(value -> Arrays.asList(value.split(",")));     //使用flatmap算子

        source.foreach((key, value) -> System.out.println(value));                                      //使用foreach 算子

        KGroupedStream groupbykeysource =mapsource.groupByKey();                                        //使用groupbykey 算子 完成分组统计

        //groupbykeysource.count("groupbykey").print();


         //KGroupedStream → CogroupedKStream      groupbykeysource ,
        KGroupedStream groupbykeysource2 =mapsource.groupByKey();


        KStream selectkeysouece = source.selectKey((key, value) -> value.split(",")[0]+new Date().getTime());
        selectkeysouece.print();

        KafkaStreams streams = new KafkaStreams(builder, props);
        streams.start();
    }
}
*/
