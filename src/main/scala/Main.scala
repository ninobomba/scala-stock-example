import cronish.dsl.task
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{avg, col, max, min}

import scala.collection.mutable.ListBuffer

object Main extends App {

  val spark = SparkSession.builder()
    .appName("Stocks")
    .config("spark.master", "local")
    .getOrCreate()

  val STOCK_APPLE_NAME: String = "APPL"

  var stockMap: Map[ String, ListBuffer[ Double ] ] = Map( STOCK_APPLE_NAME -> ListBuffer.empty )

  val stockSummarizationTask = task {

    import spark.implicits._

    val responseAPPL: String = requests.get("https://api.markets.sh/api/v1/symbols/NASDAQ:AAPL?api_token=6acd3cb107f7886c70174c413442f58b").text()
    var data = spark.read.option("inferSchema", "true").json( Seq( responseAPPL ).toDS )
    //data.show()

    stockMap( STOCK_APPLE_NAME ) += data.select( col("last_price") ).first().getAs[ Double ]( 0 )
  //  stockMap.foreach( println )

    stockMap( STOCK_APPLE_NAME ).toList.toDF( "price" ).agg( min( "price" )).show()
    stockMap( STOCK_APPLE_NAME ).toList.toDF( "price" ).agg( max( "price" )).show()
    stockMap( STOCK_APPLE_NAME ).toList.toDF( "price" ).agg( avg( "price" )).show()

  }

  stockSummarizationTask executes "every 5 seconds"

}
