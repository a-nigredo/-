package dev.nigredo.domain.model

import java.util.Date

sealed trait Storable[T] {
  val id: T
}

case class Author(id: Long, name: String, password: Password, email: Email)
case class Password(value: String)
case class Email(value: String)

case class Category(id: Long, title: String, author: Author, creationDate: Date = new Date(), parent: Option[Category] = None)
  extends Storable[Long]

case class BlogItem(id: Long, title: String, description: Option[String] = None, body: String,
                    creationDate: Date = new Date(), author: Int, categoryId: Int)
  extends Storable[Long]
