package dev.nigredo.domain.validator

object common {

  object nonEmpty extends (String => Boolean) {
    override def apply(v1: String): Boolean = v1.nonEmpty
  }

  object > extends (String => Int => Boolean) {
    override def apply(value: String): (Int) => Boolean = min => value.length > min
  }

  object < extends (String => Int => Boolean) {
    override def apply(value: String): (Int) => Boolean = max => value.length < max
  }

  object >= extends (String => Int => Boolean) {
    override def apply(value: String): (Int) => Boolean = min => value.length >= min
  }

  object <= extends (String => Int => Boolean) {
    override def apply(value: String): (Int) => Boolean = max => value.length <= max
  }

}
