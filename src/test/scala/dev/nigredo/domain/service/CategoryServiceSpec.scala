package dev.nigredo.domain.service

import cats.data.Validated.{Invalid, Valid}
import dev.nigredo.domain.model.{Author, Category, Email, Password}
import dev.nigredo.domain.service.category.{create, update}
import dev.nigredo.domain.validator.category.CategoryValidator
import dev.nigredo.repository.FindOneById
import org.specs2.mutable.Specification

class CategoryServiceSpec extends Specification {

  "Service" should {

    val author = Author(1, "name", Password("pass"), Email("test@test.com"))
    val author2 = Author(1, "updatedName", Password("updatedPass"), Email("updatedTest@test.com"))
    val category = Category(1, "cat title", author)
    val category2 = Category(1, "updated cat title", author2)

    "create category if it is valid" in {
      val validator: CategoryValidator = _ => Valid(category)
      create(category)(validator)(identity) mustEqual Valid(category)
    }
    "not create category if it is not valid" in {
      val validator: CategoryValidator = _ => Invalid(List("error"))
      create(category)(validator)(identity) mustEqual Invalid(List("error"))
    }
    "update category if it is valid" in {
      val validator: CategoryValidator = _ => Valid(category2)
      val findOne: FindOneById[Category] = _ => Option(category)
      update(category2)(validator)(findOne)(identity) mustEqual Valid(category2)
    }
    "not update category if it is not valid" in {
      val validator: CategoryValidator = _ => Invalid(List("error"))
      val findOne: FindOneById[Category] = _ => Option(category)
      update(category2)(validator)(findOne)(identity) mustEqual Invalid(List("error"))
    }
  }
}
