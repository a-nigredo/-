package dev.nigredo.domain.validator

import org.specs2.mutable.Specification
import dev.nigredo.domain.validator.impl._

class ImplSpec extends Specification {

  "NonEmpty validator" should {
    "pass" in {
      nonEmpty()(_ => "msg")("str").isValid === true
    }
    "not pass" in {
      nonEmpty()(_ => "msg")("").isInvalid === true
    }
  }
}
