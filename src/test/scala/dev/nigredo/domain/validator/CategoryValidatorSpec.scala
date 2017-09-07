package dev.nigredo.domain.validator

import dev.nigredo.domain.model.{Author, Category, Email, Password}
import dev.nigredo.domain.validator.category.{exists, nonEmptyTitle, nonExisting}
import dev.nigredo.repository.FindOneById
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
      val finder: FindOneById[Category] = _ => Option(category)
      nonExisting(finder)(category).isInvalid === true
    }
    "not pass" in {
      val category = Category(1, "cat title", author)
      val finder: FindOneById[Category] = _ => Option.empty
      nonExisting(finder)(category).isValid === true
    }
  }

  "Validator exists" should {
    "pass" in {
      val category = Category(1, "cat title", author)
      val finder: FindOneById[Category] = _ => Option(category)
      exists(finder)(category).isValid === true
    }
    "not pass" in {
      val category = Category(1, "cat title", author)
      val finder: FindOneById[Category] = _ => Option.empty
      exists(finder)(category).isInvalid === true
    }
  }
}
