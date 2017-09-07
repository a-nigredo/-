package dev.nigredo.domain.service

import dev.nigredo.CategoryResult
import dev.nigredo.domain.model.Category
import dev.nigredo.domain.validator.category.CategoryValidator
import dev.nigredo.repository.{FindOneById, Store}

object category {

  type Create = Category => CategoryValidator => Store[Category] => CategoryResult
  type Update = Category => CategoryValidator => FindOneById[Category] => Store[Category] => CategoryResult

  object create extends Create {
    override def apply(category: Category): (CategoryValidator) => (Store[Category]) => CategoryResult =
      validator => store => validator(category).map(store)
  }

  object update extends Update {
    override def apply(category: Category): (CategoryValidator) => (FindOneById[Category]) => (Store[Category]) => CategoryResult =
      validator => loader => store => validator(category).map { x =>
        store(loader(x.id).map(_.copy(title = x.title, author = x.author, parent = x.parent)).getOrElse(x))
      }
  }

}
