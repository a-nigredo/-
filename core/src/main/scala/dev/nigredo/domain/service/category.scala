package dev.nigredo.domain.service

import dev.nigredo.CategoryResult
import dev.nigredo.domain.models.Category
import dev.nigredo.domain.validator.category.{ValidationResult, Validator}
import dev.nigredo.repository.category._

object category {
  type Create = Category => Validator => Store => CategoryResult
  type Update = Category => Validator => FindOneById => Store => CategoryResult
}
