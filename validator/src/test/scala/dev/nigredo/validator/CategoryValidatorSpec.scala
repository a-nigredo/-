package dev.nigredo.validator

import dev.nigredo.domain.models.{Author, Category, Email, Password}
import dev.nigredo.repository.category.Exists
import dev.nigredo.validator.category.{exists, nonEmptyTitle, nonExisting}
import org.specs2.mutable.Specification

class CategoryValidatorSpec extends Specification {

  val author = Author(1, "name", Password("pass"), Email("test@test.com"))

  "Validator nonEmptyTitle" should {
    "pass" in {
      val category = Category(1, "cat title", author)
      nonEmptyTitle(category).isValid === true
    }
    "not pass" in {
      val category = Category(1, "", author)
      nonEmptyTitle(category).isInvalid === true
    }
  }

  "Validator nonExisting" should {
    "pass" in {
      val category = Category(1, "cat title", author)
      nonExisting(_ => true)(category).isInvalid === true
    }
    "not pass" in {
      val category = Category(1, "cat title", author)
      nonExisting(_ => false)(category).isValid === true
    }
  }

  "Validator exists" should {
    "pass" in {
      val category = Category(1, "cat title", author)
      exists(_ => false)(category).isValid === true
    }
    "not pass" in {
      val category = Category(1, "cat title", author)
      exists(_ => true)(category).isInvalid === true
    }
  }
}
