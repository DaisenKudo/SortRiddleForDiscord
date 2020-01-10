package discord

import discord4j.core.DiscordClient
import discord4j.core.`object`.entity.Message
import discord4j.core.event.domain.lifecycle.ReadyEvent
import discord4j.core.event.domain.message.MessageCreateEvent
import sortriddle.bot.Bot


abstract class SubscriberTemplate(
    private val discordClient: DiscordClient,
    private val qLimit: Int,
    private val channelId: Long
) {

    internal abstract var command: String
    internal abstract var response: String

    internal val bot = Bot

    init {
        bot.initialize(qLimit)
        discordClient.eventDispatcher.on(ReadyEvent::class.java).subscribe {
                ready -> println("onReady as ${ready.self.username}")
        }
    }

    open fun subscribe() {
        discordClient.eventDispatcher.on(MessageCreateEvent::class.java)
            .map(MessageCreateEvent::getMessage)
            .filter{message ->
                when (!message.author.get().isBot &&
                        message.channelId.asLong() == channelId) {
                    true -> message.content.map(command::equals).get()
                    false -> false
                }
            }
            .flatMap(Message::getChannel)
            .flatMap { channel ->
                channel.createMessage(response)
            }
            .subscribe()
    }
}