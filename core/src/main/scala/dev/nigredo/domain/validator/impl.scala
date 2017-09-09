package dev.nigredo.domain.validator

import cats.data.Validated
import cats.implicits._
import dev.nigredo.domain.validator.interface.Validator
import dev.nigredo.repository.interface.Exists

object impl {

  private type GenValidator[A] = ValidatorPredicate[A] => ValidationMessage[A] => Validator[A]
  private type ValidationMessage[A] = A => String
  private type ValidatorPredicate[A] = A => Boolean
  private type StringValidator = ValidationMessage[String] => Validator[String]

  def genValidator[A]: GenValidator[A] =
    predicate => msg => data => if (predicate(data)) data.valid else List(msg(data)).invalid

  def nonEmpty(): StringValidator = genValidator(_.nonEmpty)

  def >(): Int => StringValidator = bound => genValidator(_.length > bound)

  def <(): Int => StringValidator = bound => genValidator(_.length < bound)

  def >=(): Int => StringValidator = bound => genValidator(_.length >= bound)

  def <=(): Int => StringValidator = bound => genValidator(_.length <= bound)

  def exists[A]: Exists[A] => ValidationMessage[A] => Validator[A] = check => genValidator[A](check)

  def nonExists[A]: Exists[A] => ValidationMessage[A] => Validator[A] = check => genValidator[A](x => !check(x))
}
