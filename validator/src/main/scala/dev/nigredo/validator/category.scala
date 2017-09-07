package dev.nigredo.validator

import cats.implicits._
import dev.nigredo.domain.models.Category
import dev.nigredo.domain.validator.category.{ValidationResult, Validator}
import dev.nigredo.domain.validator.common._
import dev.nigredo.repository.category.Exists

object category {

  object nonEmptyTitle extends Validator {
    override def apply(cat: Category): ValidationResult =
      if(nonEmpty(cat.title)) cat.valid else List("Category title can't be empty").invalid
  }

  object nonExisting extends (Exists => Validator) {
    override def apply(exists: Exists): Validator =
      cat =>
        if(!exists(cat)) cat.valid else List(s"Category with title '${cat.title}' already exists").invalid
  }

  object exists extends (Exists => Validator) {
    override def apply(exists: Exists): Validator =
      cat =>
        if(exists(cat)) List(s"Category with title '${cat.title}' already exists").invalid else cat.valid
  }

}
