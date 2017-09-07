package dev.nigredo.repository

import dev.nigredo.domain.models.Category
import dev.nigredo.repository

object category {
  type FindOneById = repository.FindOneById[Category]
  type FindOne[Filter] = repository.FindOne[Filter, Category]
  type FindAll[Filter] = repository.FindAll[Filter, Category]
  type Exists = repository.Exists[Category]
  type Store = repository.Store[Category]
}
