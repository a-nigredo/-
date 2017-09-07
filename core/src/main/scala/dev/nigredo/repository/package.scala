package dev.nigredo

import dev.nigredo.domain.models.Storable

package object repository {
  type FindOneById[A] = Long => Option[A]
  type FindOne[Filter, B] = Filter => Option[B]
  type FindAll[Filter, B] = Filter => List[B]
  type Exists[A] = A => Boolean
  type Store[A <: Storable[_]] = A => A
}
