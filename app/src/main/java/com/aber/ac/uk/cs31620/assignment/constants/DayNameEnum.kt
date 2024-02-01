package com.aber.ac.uk.cs31620.assignment.constants

enum class DayNameEnum(val longForm: String, val shortForm: String) {
    MONDAY("Monday","Mon"),
    TUESDAY("Tuesday", "Tue"),
    WEDNESDAY("Wednesday", "Wed"),
    THURSDAY("Thursday", "Thu"),
    FRIDAY("Friday","Fri"),
    SATURDAY("Saturday", "Sat"),
    SUNDAY("Sunday", "Sun")
}

val DAYS_OF_WEEK_LONG = DayNameEnum.values().map { it.longForm }
val DAYS_OF_WEEK_SHORT = DayNameEnum.values().map { it.shortForm }
