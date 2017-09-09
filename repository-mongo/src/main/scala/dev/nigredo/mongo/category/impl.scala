package dev.nigredo.mongo.category

import com.mongodb.client.model.Filters
import dev.nigredo.domain.models.Category
import dev.nigredo.repository.category.interface._
import org.bson.conversions.Bson
import org.mongodb.scala.MongoCollection

import scala.collection.mutable.ListBuffer

object impl {

  type Collection = MongoCollection[Category]

  object findOneById extends (Collection => FindOneById) {
    override def apply(collection: Collection): FindOneById = id => findOne(collection)(Filters.eq("id", id))
  }

  object findOne extends (Collection => FindOne[Bson]) {
    override def apply(collection: Collection): FindOne[Bson] = filter => {
      val res = Array.empty[Option[Category]]
      collection.find(filter).first().subscribe((cat: Category) => res(0) = Option(cat))
      res(0)
    }
  }

  object findAll extends (Collection => FindAll[Bson]) {
    override def apply(collection: Collection): FindAll[Bson] = filter => {
      val res = ListBuffer.empty[Category]
      collection.find(filter).subscribe((cat: Category) => res += cat)
      res.toList
    }
  }

  object create extends (Collection => Store) {
    override def apply(coll: Collection): Store =
      cats => ???
  }
}
