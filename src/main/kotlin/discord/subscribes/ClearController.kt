package discord.subscribes

import discord.SubscriberTemplate
import discord4j.core.DiscordClient

class ClearController(
    discordClient: DiscordClient,
    qLimit: Int,
    channelId: Long
) : SubscriberTemplate(
    discordClient = discordClient,
    qLimit = qLimit,
    channelId = channelId
) {
    override var command: String
        get() = "-c"
        set(_) {}
    override var response: String
        get() = bot.clear()
        set(_) {}

    init {
        subscribe()
    }
}
