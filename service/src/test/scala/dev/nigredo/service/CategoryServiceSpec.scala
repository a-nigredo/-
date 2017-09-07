package dev.nigredo.service

import cats.data.Validated.{Invalid, Valid}
import dev.nigredo.ValidationError
import dev.nigredo.domain.models.{Author, Category, Email, Password}
import dev.nigredo.domain.validator.category.Validator
import dev.nigredo.repository.category.FindOneById
import dev.nigredo.service.category.{create, update}
import org.specs2.mutable.Specification

class CategoryServiceSpec extends Specification {

  "Service" should {

    val author = Author(1, "name", Password("pass"), Email("test@test.com"))
    val author2 = Author(1, "updatedName", Password("updatedPass"), Email("updatedTest@test.com"))
    val category = Category(1, "cat title", author)
    val category2 = Category(1, "updated cat title", author2)

    "create category if it is valid" in {
      val validator: Validator = _ => Valid(category)
      create(category, validator, identity) must beRight(category)
    }
    "not create category if it is not valid" in {
      val validator: Validator = _ => Invalid(List("error"))
      create(category, validator, identity) must beLeft(ValidationError(List("error")))
    }
    "update category if it is valid" in {
      val validator: Validator = _ => Valid(category2)
      val findOne: FindOneById = _ => Option(category)
      update(category2, validator, findOne, identity) must beRight(category2)
    }
    "not update category if it is not valid" in {
      val validator: Validator = _ => Invalid(List("error"))
      val findOne: FindOneById = _ => Option(category)
      update(category2, validator, findOne, identity) must beLeft(ValidationError(List("error")))
    }
  }
}
