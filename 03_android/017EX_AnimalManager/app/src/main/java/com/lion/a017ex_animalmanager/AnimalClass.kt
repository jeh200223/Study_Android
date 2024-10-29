import com.lion.a017ex_animalmanager.databinding.ActivityMainBinding

class AnimalClass(
    val name: String,
    val age: Int,
    val type: String,
    val like: List<String>,
    val gender: String
) {
    fun showAnimalInfo(activityMainBinding: ActivityMainBinding) {
        activityMainBinding.apply {
            textResult.append(
                "이름 : ${name}\n" +
                        "나이 : ${age}살\n" +
                        "동물의 종류 : ${type}\n" +
                        "좋아하는 간식 : ${like.joinToString(",")}\n" +
                        "성별 : ${gender}\n\n"
            )
        }
    }
}
