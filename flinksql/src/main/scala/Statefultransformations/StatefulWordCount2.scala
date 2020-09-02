/*
package Statefultransformations

import java.lang
import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}
import org.apache.kafka.streams.kstream.{Aggregator, Initializer, KGroupedStream, KStream, KStreamBuilder, KTable, KeyValueMapper}
import org.apache.kafka.streams.processor.{ProcessorContext, StateStore, TopologyBuilder}
import org.apache.kafka.streams.scala.Serdes
import org.apache.kafka.streams.scala.kstream.Materialized

object StatefulWordCount2 {
  def main(args: Array[String]): Unit = {
    /**1.基本连接配置*/
    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "DSLopreation")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "jiaxun:9092")
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")


    val builder: KStreamBuilder = new KStreamBuilder()

    val stream: KStream[String, String] =  builder.stream("sourcetopic")

    val filterstream: KStream[String, String] = stream.filter((key, value) =>value.contains("name"))

    val selectbykeysource: KStream[String, String] = stream.selectKey[String]((key, value) =>value)
    //val mapsource: KStream[String, String] = stream.map[String,String]((key, value) =>(value,value) )
    val groupbysource: KStream[Nothing, lang.Long] =stream.groupBy((_, word) => word).count("sotrevalue").toStream
    groupbysource.print()

  //  val groupbysource: KGroupedStream[String, String] = stream.groupByKey()



   /* val aggresource =groupbysource.aggregate(new Initializer[Long] {
      override def apply(): Long = 0L
    },new Aggregator[String,String,Long] {
      override def apply(key: String, value: String, aggregate: Long): Long = aggregate +1L
    },new StateStore {
      override def name(): String = ???

      override def init(context: ProcessorContext, root: StateStore): Unit = ???

      override def flush(): Unit = ???

      override def close(): Unit = ???

      override def persistent(): Boolean = ???

      override def isOpen: Boolean = ???
    }
    )
*/

    val kafkaStream =new KafkaStreams(builder,props)
    kafkaStream.start()
  }

}
*/
