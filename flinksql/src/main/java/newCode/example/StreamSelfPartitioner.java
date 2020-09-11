package newCode.example;

import newCode.model.Purchase;
import org.apache.kafka.streams.processor.StreamPartitioner;

//自定义分区
public  class StreamSelfPartitioner implements StreamPartitioner<String,Purchase>{

    @Override
    public Integer partition(String topic, String key, Purchase value, int numPartitions) {
        return value.getCustomerId().hashCode()%numPartitions;
    }
}
