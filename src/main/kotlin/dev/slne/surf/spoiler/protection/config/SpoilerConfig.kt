package dev.slne.surf.spoiler.protection.config

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class SpoilerConfig(
    val enabled: Boolean = true,
    val players: MutableList<String> = mutableListOf("CastCrafter")
)
