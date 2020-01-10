package discord.subscribes

import discord.SubscriberTemplate
import discord4j.core.DiscordClient
import discord4j.core.`object`.entity.Message
import discord4j.core.event.domain.message.MessageCreateEvent

class JudgeController(
    private val discordClient: DiscordClient,
    private val qLimit: Int,
    private val channelId: Long
) : SubscriberTemplate(
    discordClient = discordClient,
    qLimit = qLimit,
    channelId = channelId
) {
    override var command: String
        get() = "-a [^ ]+"
        set(_) {}

    override var response: String
        get() = ""
        set(_) {}

    init {
        subscribe()
    }

    override fun subscribe() {
        var mes: String? = null
        var user: String? = null

        discordClient.eventDispatcher.on(MessageCreateEvent::class.java)
            .map(MessageCreateEvent::getMessage)
            .filter{ message ->
                when (!message.author.get().isBot && message.channelId.asLong() == channelId) {
                    true -> {
                        mes = message.content.get()
                        user = message.author.get().mention
                        return@filter message.content.map(command.toRegex()::containsMatchIn).get()
                    }
                    false -> return@filter false
                }
            }
            .flatMap(Message::getChannel)
            .flatMap { channel ->
                if (mes is String) {
                    mes = mes!!.substring(3 until mes!!.length)
                }
                    return@flatMap channel.createMessage("${user}\n${bot.judge(mes!!)}")

            }
            .subscribe()
    }
}
