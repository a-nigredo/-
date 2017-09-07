package dev

import cats.data.Validated
import dev.nigredo.domain.model.Category

package object nigredo {

  type CategoryResult = Result[Category]
  type Result[A] = Validated[List[String], A]

}
