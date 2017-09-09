package dev.nigredo

sealed trait Error

case class ValidationError(msgs: List[String]) extends Error
