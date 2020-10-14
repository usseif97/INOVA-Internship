package com.inovaeg.androidtrainingpharos2020.dynamicFragment2

import android.os.Parcelable

abstract class FragmentItem : Parcelable {

    var num = 0
    var col = 0

    constructor(number: Int, color: Int) {
        num = number
        col = color
    }

    fun setNumber(number: Int) {
        num = number
    }

    fun setColor(color: Int) {
        col = color
    }

    fun getNumber(): Int {
        return num
    }

    fun getColor(): Int {
        return col
    }

}
