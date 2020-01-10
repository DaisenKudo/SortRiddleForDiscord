import com.google.gson.JsonParser
import discord.subscribes.*
import discord4j.core.DiscordClientBuilder
import kotlinx.coroutines.MainScope
import java.io.File
import java.nio.charset.Charset

fun main() {
    val token: String
    val channelId: Long
    val questionLimit: Int

    val path = "/opt/settings.json"

    val settings = JsonParser().parse(File(path).readText()).asJsonObject

    token = settings["token"].asString
    channelId = settings["channelid"].asString.toLong()
    questionLimit = settings["qlimit"].asString.toInt()


    DiscordClientBuilder(token).build()
        .let {
            QuestionController(it, questionLimit, channelId)
            JudgeController(it, questionLimit, channelId)
            GiveupController(it, questionLimit, channelId)
            ClearController(it, questionLimit, channelId)
            HelpController(it, questionLimit, channelId)

            it.login().block()
        }
}