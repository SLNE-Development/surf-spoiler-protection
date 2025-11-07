package dev.slne.surf.spoiler.protection

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.spoiler.protection.command.spoilerProtectionCommand
import dev.slne.surf.spoiler.protection.config.SpoilerConfiguration
import dev.slne.surf.spoiler.protection.listener.PlayerJoinListener
import dev.slne.surf.surfapi.bukkit.api.event.register
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(PaperMain::class.java)

class PaperMain : SuspendingJavaPlugin() {
    override fun onEnable() {
        spoilerProtectionCommand()

        PlayerJoinListener.register()
    }

    val configuration = SpoilerConfiguration()
}

val config get() = plugin.configuration.config