package dev.slne.surf.spoiler.protection.config

import dev.slne.surf.spoiler.protection.plugin
import dev.slne.surf.surfapi.core.api.config.manager.SpongeConfigManager
import dev.slne.surf.surfapi.core.api.config.surfConfigApi

class SpoilerConfiguration {
    private val configManager: SpongeConfigManager<SpoilerConfig>

    init {
        surfConfigApi.createSpongeYmlConfig(
            SpoilerConfig::class.java,
            plugin.dataPath,
            "config.yml"
        )
        configManager = surfConfigApi.getSpongeConfigManagerForConfig(
            SpoilerConfig::class.java
        )
        reload()
    }

    fun edit(block: SpoilerConfig.() -> Unit) {
        configManager.config = configManager.config.apply(block)
        configManager.save()
    }

    fun reload() {
        configManager.reloadFromFile()
    }

    val config get() = configManager.config
}