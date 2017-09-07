package dev

import dev.nigredo.domain.models.Category

package object nigredo {
  type CategoryResult = Result[Category]
  type Result[A] = Either[dev.nigredo.Error, A]
}
