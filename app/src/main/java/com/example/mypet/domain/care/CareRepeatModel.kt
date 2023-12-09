package com.example.mypet.domain.care

data class CareRepeatModel(
    val id: Int,

    var intervalTimes: String,
    var intervalOrdinal: Int?,

    var isMonday: Boolean,
    var isTuesday: Boolean,
    var isWednesday: Boolean,
    var isThursday: Boolean,
    var isFriday: Boolean,
    var isSaturday: Boolean,
    var isSunday: Boolean,

    var endTypeOrdinal: Int,
    var endAfterTimes: String,
    var endAfterDate: Long,
) : CareAdapterModel(CARE_ADAPTER_REPEAT_KEY)