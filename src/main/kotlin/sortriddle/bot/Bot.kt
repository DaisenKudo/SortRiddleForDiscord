package sortriddle.bot

import sortriddle.questions.wikipedia.Wikipedia
import sortriddle.shape.Shape


/**
 * Bot (Singleton)
 */
object Bot {
    private val wikipedia = Wikipedia()
    private var qStr: String? = null
    private var aStr: String? = null

    private var qLimit: Int = 0

    /**
     * 最初にここにアクセスすることでSingletonの生成がなされる
     */
    fun initialize(ql: Int = 0): String {
        this.qLimit = ql
        return "参加ずら〜"
    }

    fun question(): String {
        if (qStr == null) {
            aStr = wikipedia.getRandomWikiTitle()

            //設定未満の文字数だった場合再取得
            while ((aStr?.length ?: throw KotlinNullPointerException("Wikipediaにアクセスできませんでした。")) < qLimit) {
                aStr = wikipedia.getRandomWikiTitle()
            }
            qStr = Shape.randomize(aStr ?: throw KotlinNullPointerException("Wikipediaにアクセスできませんでした。"))
        }

        return "問題は「**${qStr}**」ずら。"
    }

    fun judge(str: String): String {
        when {
            aStr == null -> {
                return "問題は出題してません。¥n" +
                        "/rで出題します。"
            }
            str == aStr -> {
                val url = "https://ja.wikipedia.org/wiki/$aStr"

                clear()
                return """
                    正解ずら！
                    $url
                """.trimIndent()
            }
            str.length == aStr?.length -> {
                var num = 0

                str.forEachIndexed { i, c ->
                    num += if (aStr?.get(i) == c) 1 else 0
                }

                return "${num}文字だけ合ってるずら。"
            }
            else -> {
                return "文字数が違うずら。"
            }
        }
    }

    fun giveup(): String {
        if (aStr == null) {
            return "問題は出題してません。¥n" +
                    "/rで出題します。"
        }
        val ans = aStr
        clear()
        return "正解は${ans}ずら。"
    }

    fun clear(): String {
        qStr = null
        aStr = null

        return "リセットしました。"
    }

    fun help(): String = """
        マルが知識の海から単語を拾ってきてランダムに並べ替えるから、それを当てるゲームをするずら！
        
        -r : 出題します
        -a (Answer) : 解答を受け付けます
        -g : ギブアップずらか？
        -c : 出題取り消しです
        -h : 説明です
    """.trimIndent()
}