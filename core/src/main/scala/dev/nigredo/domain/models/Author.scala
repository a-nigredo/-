package dev.nigredo.domain.models

case class Author(id: Long, name: String, password: Password, email: Email)

case class Password(value: String)

case class Email(value: String)
