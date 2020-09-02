/*
import org.apache.flink.runtime.topology.Topology;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.StreamsException;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.scala.StreamsBuilder;


import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

*/
/**
 * 1.单词统计*//*


public class kafkaStreamDemo {
    public static void main(String[] args) throws Exception {

        */
/**1.基本连接配置*//*

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "jiaxun:9092");
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());  //序列化和反序列化
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        */
/**2.构建 KStreamBuilder  和  KStream,*//*

        KStreamBuilder builder = new KStreamBuilder();


        KStream<String, String> source = builder.stream("test-kafkaStream");         //设定需要消费的主题，得到KStram对象

        //对value进行操作，构造一个ValueMapper, 从指定主题中获得数据进行 处理，并得到 KTabled对象
        final KTable<String, Long> counts = source.flatMapValues(new ValueMapper<String, Iterable<String>>() {
            @Override
            public Iterable<String> apply(String value) {                             //apply处理每一条数据
                return Arrays.asList(value.toLowerCase(Locale.getDefault()).split(","));
            }
        }).map(new KeyValueMapper<String, String, KeyValue<String, ?>>() {
            @Override
            public KeyValue<String, String> apply(String key, String value) {//只取value,按照单词进行分组
                return new KeyValue<>(value, value);
            }
        }).groupByKey().count("countstore");
        counts.print();                     //无状态算子print

        final KafkaStreams streams = new KafkaStreams(builder, props);      //开启KafkaStreams,传入 KStreamBuilder 和 Properties


        //启动与关闭,开启一个任务执行
        final CountDownLatch latch = new CountDownLatch(1);


        //线程完毕以后释放流
        Runtime.getRuntime().addShutdownHook(new Thread("word-count") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();//流关闭的同时，latch值变为0
            }
        });

        try {
            streams.start();
            latch.await();//线程被挂起,等待latch的值变为0才重新开始执行
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (StreamsException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
*/
