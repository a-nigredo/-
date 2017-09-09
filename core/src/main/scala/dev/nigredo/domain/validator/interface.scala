package dev.nigredo.domain.validator

import cats.data.Validated

object interface {
  type Validator[A] = A => ValidationResult[A]
  type ValidationResult[A] = Validated[List[String], A]
}
