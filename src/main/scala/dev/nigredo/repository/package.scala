package dev.nigredo

package object repository {
  type FindOneById[A] = Long => Option[A]
  type FindOne[Filter, B] = Filter => Option[B]
  type FindAll[Filter, B] = Filter => List[B]
  type ExistsById = Long => Boolean
  type Store[A] = A => A
}
