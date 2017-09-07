package dev.nigredo.repository.mongo

import com.mongodb.client.model.Filters
import dev.nigredo.domain.model.Category
import dev.nigredo.repository._
import org.bson.conversions.Bson
import org.mongodb.scala.{Completed, MongoCollection, Observer}

import scala.collection.mutable.ListBuffer

object category {

  type Collection = MongoCollection[Category]

  object findOneById extends (Collection => FindOneById[Category]) {
    override def apply(collection: Collection): FindOneById[Category] = id => findOne(collection)(Filters.eq("id", id))
  }

  object findOne extends (Collection => FindOne[Bson, Category]) {
    override def apply(collection: Collection): FindOne[Bson, Category] = filter => {
      val res = Array.empty[Option[Category]]
      collection.find(filter).first().subscribe((cat: Category) => res(0) = Option(cat))
      res(0)
    }
  }

  object findAll extends (Collection => FindAll[Bson, Category]) {
    override def apply(collection: Collection): FindAll[Bson, Category] = filter => {
      val res = ListBuffer.empty[Category]
      collection.find(filter).subscribe((cat: Category) => res += cat)
      res.toList
    }
  }

  object create extends (Collection => Store[Category]) {
    override def apply(coll: Collection): Store[Category] =
      cats => coll.insertOne(cats).subscribe(new Observer[Completed] {

        override def onNext(result: Completed): Unit = println("Inserted")

        override def onError(e: Throwable): Unit = println("Failed")

        override def onComplete(): Unit = println("Completed")
      })
  }

}
