package com.nikolovlazar.goodbyemoney.models

sealed class Recurrence(val name: String, val target: String) {
  object None : Recurrence("Нету", "Нету")
  object Daily : Recurrence("Каждый день", "Сегодня")
  object Weekly : Recurrence("Каждую неделю", "Эта неделя")
  object Monthly : Recurrence("Каждый месяц", "Этот месяц")
  object Yearly : Recurrence("Каждый год", "Этот год")
}

fun String.toRecurrence(): Recurrence {
  return when(this) {
    "None" -> Recurrence.None
    "Daily" -> Recurrence.Daily
    "Weekly" -> Recurrence.Weekly
    "Monthly" -> Recurrence.Monthly
    "Yearly" -> Recurrence.Yearly
    else -> Recurrence.None
  }
}