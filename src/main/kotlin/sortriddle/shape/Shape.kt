package sortriddle.shape

import java.lang.StringBuilder

object Shape {
    private val supportChar = Regex("[^0-9a-zA-Zぁ-んァ-ヶ]")
    private val unnecessaryList = listOf(
        " (曖昧さ回避)"
    )

    //fun <T> List<T>.random(random: Random): T? = if (size > 0) get(random.nextInt(size)) else null

    fun isSupportName(str: String): Boolean = !supportChar.containsMatchIn(str)

    fun trim(str: String): String {
        var nStr = str
        unnecessaryList.forEach { nStr = nStr.replace(it, "") }
        return nStr
    }

    fun randomize(str: String): String {
        val nStr = StringBuilder(20)
        str.toList().shuffled().take(str.length).forEach { nStr.append(it) }
        return nStr.toString()
    }
}
