package dev.nigredo.service

import dev.nigredo.domain.models.Category
import dev.nigredo.domain.service.category.{Create, Update}
import dev.nigredo.domain.validator.category.Validator
import dev.nigredo.repository.category.{FindOneById, Store}
import dev.nigredo.{CategoryResult, ValidationError}

object category {

  object create extends Create {
    override def apply(category: Category): (Validator) => (Store) => CategoryResult =
      validator => store =>
        validator(category).leftMap(ValidationError).map(store).toEither
  }

  object update extends Update {
    override def apply(category: Category): (Validator) => (FindOneById) => (Store) => CategoryResult =
      validator => loader => store =>
        validator(category).leftMap(ValidationError).map { x =>
          store(loader(x.id).map(_.copy(title = x.title, author = x.author, parent = x.parent)).getOrElse(x))
        }.toEither
  }

}
