package dev.nigredo.repository

import dev.nigredo.domain.model.{Author, Category, Email, Password}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}

package object mongo {

  import org.mongodb.scala._

  private val codecRegistry = fromRegistries(fromProviders(classOf[Category], classOf[Author], classOf[Password], classOf[Email]), DEFAULT_CODEC_REGISTRY)
  private val database = MongoClient("mongodb://localhost").getDatabase("blog").withCodecRegistry(codecRegistry)

  object collections {
    lazy val category: MongoCollection[Category] = database.getCollection("category")
  }
}
