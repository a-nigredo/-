package dev.nigredo.domain.validator

import cats.data.Validated
import dev.nigredo.domain.models.Category

object category {
  type Validator = Category => ValidationResult
  type ValidationResult = Validated[List[String], Category]
}
