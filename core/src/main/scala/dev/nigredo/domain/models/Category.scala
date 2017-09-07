package dev.nigredo.domain.models

import java.util.Date

case class Category(id: Long, title: String, author: Author, creationDate: Date = new Date(), parent: Option[Category] = None)
  extends Storable[Long]
