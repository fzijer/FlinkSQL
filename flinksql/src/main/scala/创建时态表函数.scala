/*

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.EnvironmentSettings
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment

import scala.collection.mutable

object 创建时态表函数 {
  def main(args: Array[String]): Unit = {
    // 获取 stream 和 table 环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val tEnv = StreamTableEnvironment.create(env)

    // 提供一个汇率历史记录表静态数据集
    val ratesHistoryData = new mutable.MutableList[(String, Long)]
    ratesHistoryData.+=(("US Dollar", 102L))
    ratesHistoryData.+=(("Euro", 114L))
    ratesHistoryData.+=(("Yen", 1L))
    ratesHistoryData.+=(("Euro", 116L))
    ratesHistoryData.+=(("Euro", 119L))

    // 用上面的数据集创建并注册一个示例表
    // 在实际设置中，应使用自己的表替换它
    val ratesHistory = env
      .fromCollection(ratesHistoryData)
      .toTable(tEnv, 'r_currency, 'r_rate, 'r_proctime.proctime)

    tEnv.createTemporaryView("RatesHistory", ratesHistory)

    // 创建和注册时态表函数
    // 指定 "r_proctime" 为时间属性，指定 "r_currency" 为主键
    val rates = ratesHistory.createTemporalTableFunction($"r_proctime", $"r_currency") // <==== (1)
    tEnv.registerFunction("Rates", rates)                                          // <==== (2)
    /*行(1)创建了一个 rates 时态表函数， 这使我们可以在 Table API 中使用 rates 函数。

行(2)在表环境中注册名称为 Rates 的函数，这使我们可以在 SQL 中使用 Rates 函数。*/
  }

}
*/
