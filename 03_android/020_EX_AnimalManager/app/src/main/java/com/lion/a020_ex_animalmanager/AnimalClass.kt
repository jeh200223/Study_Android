package com.lion.a020_ex_animalmanager

import com.lion.a020_ex_animalmanager.databinding.ActivityMainBinding

class AnimalClass(val type: String, val name: String, val age: Int, val gender: String, val weight: List<Int>, val favorite: List<String>) {

    fun showAnimalInfo(activityMainBinding: ActivityMainBinding) {
        activityMainBinding.apply {
            textResult.append("동물의 종류 : ${type}\n" +
                    "이름 : ${name}\n" +
                    "나이 : ${age}살\n" +
                    "성별 : ${gender}\n" +
                    "동물의 몸무게 : ${weight[0]} ~ ${weight[1]}kg\n" +
                    "좋아하는 간식 : ${favorite.joinToString(",")}\n")
        }
    }
}