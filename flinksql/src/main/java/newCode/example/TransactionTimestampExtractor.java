package newCode.example;


import newCode.model.Purchase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

/*设置 时间类型  */
public class TransactionTimestampExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> record, long previousTimestamp) {
        Purchase purchasePurchaseTransaction = (Purchase) record.value();
        return purchasePurchaseTransaction.getPurchaseDate().getTime();
    }
}
