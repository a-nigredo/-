package dev.nigredo.domain.service.category

import dev.nigredo.domain.models.Category

object interface {
  type Create = dev.nigredo.domain.service.interface.Create[Category]
  type Update = dev.nigredo.domain.service.interface.Update[Category]
}
