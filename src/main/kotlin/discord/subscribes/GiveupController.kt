package discord.subscribes

import discord.SubscriberTemplate
import discord4j.core.DiscordClient

class GiveupController(
    private val discordClient: DiscordClient,
    private val qLimit: Int,
    private val channelId: Long
) : SubscriberTemplate(
    discordClient = discordClient,
    qLimit = qLimit,
    channelId = channelId
) {
    override var command: String
        get() = "-g"
        set(_) {}
    override var response: String
        get() = bot.giveup()
        set(_) {}

    init {
        subscribe()
    }
}