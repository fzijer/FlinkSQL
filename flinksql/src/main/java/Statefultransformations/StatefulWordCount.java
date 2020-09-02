/*
package Statefultransformations;

import org.apache.flink.calcite.shaded.com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import sun.rmi.runtime.Log;
//import org.apache.kafka.streams.kstream.Materialized;


import java.util.Map;
import java.util.Properties;

*/
/**1.使用 带状态的方式 来统计单词 个数 *//*

public class StatefulWordCount {
    private static JsonDeserialize Materialized;
    // private static JsonDeserialize Materialized;

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

        KGroupedStream keybysource = source.groupBy((key, value) -> value);
        //KStream countsource = keybysource.count("wordcount").toStream();

        */
/*Materialized.as("aggregated-stream-store")
        .withValueSerde(Serdes.Long());*//*

        //Materialized
        KTable agregatesource = keybysource.aggregate(new Initializer<Long>() {
                                                          @Override
                                                          public Long apply() {
                                                              return 0L;
                                                          }
                                                      }, new Aggregator<String,String,Long>() {
                                                          @Override
                                                          public Long apply(String key, String value, Long aggregate) {
                                                              return aggregate+1;
                                                          }
                                                      }, new Serde() {
                                                          @Override
                                                          public void configure(Map map, boolean b) {

                                                          }

                                                          @Override
                                                          public void close() {

                                                          }

                                                          @Override
                                                          public Serializer serializer() {
                                                              return null;
                                                          }

                                                          @Override
                                                          public Deserializer deserializer() {
                                                              return null;
                                                          }
                                                      },"storename"
        );
        agregatesource.print();


        KafkaStreams streams = new KafkaStreams(builder, props);
        streams.start();

    }
}
*/
