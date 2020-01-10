package discord.subscribes

import discord.SubscriberTemplate
import discord4j.core.DiscordClient

class HelpController(
    discordClient: DiscordClient,
    qLimit: Int,
    channelId: Long
) : SubscriberTemplate(
    discordClient = discordClient,
    qLimit = qLimit,
    channelId = channelId
) {
    override var command: String
        get() = "-h"
        set(_) {}
    override var response: String
        get() = bot.help()
        set(_) {}

    init {
        subscribe()
    }
}