package dev.nigredo.domain.validator

object common {

  private type Length = (String, Int) => Boolean

  object nonEmpty extends (String => Boolean) {
    override def apply(v1: String): Boolean = v1.nonEmpty
  }

  object > extends Length {
    override def apply(value: String, min: Int): Boolean = value.length > min
  }

  object < extends Length {
    override def apply(value: String, max: Int): Boolean = value.length < max
  }

  object >= extends Length {
    override def apply(value: String, min: Int): Boolean = value.length >= min
  }

  object <= extends Length {
    override def apply(value: String, max: Int): Boolean = value.length <= max
  }

}
