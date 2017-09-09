package dev.nigredo.domain.service

import dev.nigredo.ValidationError
import dev.nigredo.domain.models.Storable
import dev.nigredo.domain.service.interface.{Create, Update}

object impl {
  def create[A <: Storable[_]]: Create[A] =
    validator => store => data =>
      validator(data).leftMap(ValidationError).map(store).toEither

  def update[A <: Storable[_]]: Update[A] =
    validate => update => store => data =>
      validate(data).leftMap(ValidationError).map(x => store(update(x))).toEither
}
