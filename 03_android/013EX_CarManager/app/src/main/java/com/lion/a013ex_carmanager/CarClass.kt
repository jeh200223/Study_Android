package com.lion.a013ex_carmanager

import android.util.Log

class CarClass(val name: String, val type: String, val distance: Int) {
    fun showCarInfo() {
        Log.d("car", "이름 : $name")
        Log.d("car", "자동차 종류 : $type")
        Log.d("car", "연비 : $distance")
    }
}