package dev.nigredo.constraint.category

import dev.nigredo.constraint.category.impl.category
import dev.nigredo.domain.models.{Author, Category, Email, Password}
import org.specs2.mutable.Specification

class ImplSpec extends Specification {

  val author = Author(1, "name", Password("pass"), Email("test@test.com"))

  "Constraint for new category" should {
    "pass when title is not empty, it does not exists and empty parent" in {
      category(_ => false)(Category(1, "cat title", author)).isValid === true
    }
    "pass when title is not empty, it does not exists and parent is not itself" in {
      val parent = Category(2, "parent", author)
      val cat = Category(1, "cat title", author, parent = Option(parent))
      category(_ => false)(cat).isValid === true
    }
    "not pass when title is empty" in {
      category(_ => false)( Category(1, "", author)).isInvalid === true
    }
    "not pass when title is not empty but exists" in {
      category(_ => true)(Category(1, "cat title", author)).isInvalid === true
    }
    "not pass when category is parent to itself" in {
      val cat = Category(1, "", author)
      category(_ => false)(cat.copy(parent = Option(cat))).isInvalid === true
    }
  }
}
