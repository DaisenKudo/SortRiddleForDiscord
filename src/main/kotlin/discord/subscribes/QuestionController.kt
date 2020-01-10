package discord.subscribes

import discord.SubscriberTemplate
import discord4j.core.DiscordClient

class QuestionController(
    discordClient: DiscordClient,
    qLimit: Int,
    channelId: Long
): SubscriberTemplate(
    discordClient = discordClient,
    qLimit = qLimit,
    channelId = channelId
) {
    override var command: String
        get() = "-r"
        set(_) {}
    override var response: String
        get() = bot.question()
        set(_) {}

    init {
        subscribe()
    }
}