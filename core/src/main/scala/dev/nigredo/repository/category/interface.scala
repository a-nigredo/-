package dev.nigredo.repository.category

import dev.nigredo.domain.models.Category

object interface {
  type FindOneById = dev.nigredo.repository.interface.FindOneById[Category]
  type FindOne[Filter] = dev.nigredo.repository.interface.FindOne[Filter, Category]
  type FindAll[Filter] = dev.nigredo.repository.interface.FindAll[Filter, Category]
  type Exists = dev.nigredo.repository.interface.Exists[Category]
  type Store = dev.nigredo.repository.interface.Store[Category]
}
