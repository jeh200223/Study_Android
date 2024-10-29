package com.lion.a020_ex_animalmanager2

data class AnimalClass(var animalType: AnimalType, var animalName:String,
                       var animalAge:Int, var animalGender: AnimalGender,
                       var animalMinWeight:Int, var animalMaxWeight:Int, var animalFood: MutableList<AnimalFood>)

// 동물의 종류
enum class AnimalType(var number:Int, var str:String){
    TYPE_DOG(1, "강아지"),
    TYPE_CAT(2, "고양이"),
    TYPE_PARROT(3, "앵무새")
}

// 성별
enum class AnimalGender(var number:Int, var str:String){
    GENDER_MALE(1, "숫컷"),
    GENDER_FEMALE(2, "암컷")
}

// 간식
enum class AnimalFood(var number:Int, var str:String){
    FOOD_APPLE(1, "사과"),
    FOOD_BANANA(2, "바나나"),
    FOOD_JUJUBE(3, "대추")
}