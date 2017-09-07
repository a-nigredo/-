package dev.nigredo.domain.validator

import cats.syntax.either._
import dev.nigredo.CategoryResult
import dev.nigredo.domain.model.Category
import dev.nigredo.domain.validator.common._
import dev.nigredo.repository.ExistsById

object category {

  type CategoryValidator = Category => CategoryResult

  object nonEmptyTitle extends CategoryValidator {
    override def apply(cat: Category): CategoryResult =
      Right(cat).ensure(List("Category title can't be empty"))(x => nonEmpty(x.title)).toValidated
  }

  object nonExisting extends (ExistsById => CategoryValidator) {
    override def apply(exists: ExistsById): CategoryValidator =
      cat =>
        Right(cat).ensure(List(s"Category with title '${cat.title}' already exists"))(x => exists(x.id)).toValidated
  }

  object exists extends (ExistsById => CategoryValidator) {
    override def apply(exists: ExistsById): CategoryValidator =
      cat =>
        Right(cat).ensure(List(s"Category with title '${cat.title}' already exists"))(x => exists(x.id)).toValidated
  }

}
