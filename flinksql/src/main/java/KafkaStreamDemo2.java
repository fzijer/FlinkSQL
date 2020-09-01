import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.processor.TopologyBuilder;

import java.util.Properties;

/**
 * 2.使用KafkaStream 某一主题中的数据 简单处理后写入到另一个 主题 中   (建立 拓扑TopologyBuilder 的方式)
 */
public class KafkaStreamDemo2 {
    public static void main(String[] args) {
        //设定输入 与输出的 主题名
        String sourcetopic = "sourcetopic";
        String sinktopic = "sinktopic";

        //申明 配置来连接Kafka  StreamsConfig
        Properties setting = new Properties();
        setting.put(StreamsConfig.APPLICATION_ID_CONFIG, "topicTotopic");     //流处理标识，对应一个应用需要保持一致，用作消费的group.id
        setting.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "jiaxun:9092");   //指定要连接的kafka客户端
      /*  setting.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName()); //序列化与反序列化
        setting.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());*/

        StreamsConfig properties = new StreamsConfig(setting);

        //构建拓扑TopologyBuilder  来执行 一系列的执行计划
        TopologyBuilder builder = new TopologyBuilder();
        builder.addSource("source1", sourcetopic);   //加入数据源 主题为 sourcetopic    name 参数 用来唯一标识 该数据源
        builder.addProcessor("processor1", new ProcessorSupplier<byte[], byte[]>() {
            @Override
            public Processor<byte[], byte[]> get() {
                //该 处理器中 处理数据的核心代码
                return new LogProcessor();
            }
        }, "source1");                           //加入一个处理器

        builder.addSink("sink1", sinktopic, "processor1");


        //创建KafkaStreams
        KafkaStreams streams = new KafkaStreams(builder, properties); //传入 TopologyBuilder 和  StreamsConfig
        streams.start();
    }
}


//2.定义一个处理器 实现Processor 接口 来完成数据的处理
class LogProcessor implements Processor<byte[], byte[]> {
    private ProcessorContext context;            //声明 ProcessorContext

    @Override       //用来初始化
    public void init(ProcessorContext context) {
        this.context = context;
    }

    @Override                                   //用来处理 topic中每一条数据数据
    public void process(byte[] key, byte[] value) {
        String input = new String(value);
        if (input.contains("<<<")) {
            input = input.split("<<<")[1].trim();
            System.out.println("经过处理后的数据" + input);

            //使用  forword 输出到另一个 topic中
            context.forward("LogProcessor".getBytes(), input.getBytes());
        } else {
            context.forward("LogProcessor".getBytes(), input.getBytes());
        }
    }

    @Override
    public void punctuate(long timestamp) {

    }

    @Override
    public void close() {

    }
}
