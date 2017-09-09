package dev.nigredo

import dev.nigredo.domain.models.Category

object interface {
  type CategoryResult = Result[Category]
  type Result[A] = Either[dev.nigredo.Error, A]
}
