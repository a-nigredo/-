package dev.nigredo.constraint.category

import dev.nigredo.domain.models.Category
import dev.nigredo.domain.validator.category.interface.CategoryValidator
import dev.nigredo.domain.validator.impl._
import dev.nigredo.repository.category.interface.Exists

object impl {

  object category extends (Exists => CategoryValidator) {
    override def apply(exists: Exists): CategoryValidator =
      cat =>
        nonEmpty()(_ => "Title can't be empty")(cat.title).map(_ => cat)
          .andThen(nonExists[Category](exists)(x => s"Category with title ${x.title} exists"))
          .andThen(genValidator[Category](x => !x.parent.contains(x))(_ => "Category can't be set as a parent to itself"))
  }

}
