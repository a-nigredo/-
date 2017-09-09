package dev.nigredo.domain.validator.category

import dev.nigredo.domain.models.Category
import dev.nigredo.domain.validator.interface.{ValidationResult, Validator}

object interface {
  type CategoryValidator = Validator[Category]
  type CategoryValidationResult = ValidationResult[Category]
}
