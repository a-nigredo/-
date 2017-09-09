package dev.nigredo.domain.service

import dev.nigredo.domain.models.Storable
import dev.nigredo.domain.validator.interface.Validator
import dev.nigredo.interface.Result
import dev.nigredo.repository.interface.Store

object interface {
  type Updater[A] = A => A
  type Create[A <: Storable[_]] = Validator[A] => Store[A] => A => Result[A]
  type Update[A <: Storable[_]] = Validator[A] => Updater[A] => Store[A] => A => Result[A]
}
