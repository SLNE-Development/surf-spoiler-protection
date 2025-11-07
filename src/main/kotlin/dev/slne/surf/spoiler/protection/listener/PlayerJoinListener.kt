package dev.slne.surf.spoiler.protection.listener

import dev.slne.surf.spoiler.protection.config
import dev.slne.surf.spoiler.protection.dialog.warningDialog
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object PlayerJoinListener : Listener {
    @EventHandler
    fun onConfigurationEnter(event: PlayerJoinEvent) {
        val player = event.player

        if (!config.enabled) {
            return
        }

        if (config.players.contains(player.name)) {
            player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.BLINDNESS,
                    PotionEffect.INFINITE_DURATION,
                    255,
                    false,
                    false,
                    false
                )
            )
            player.showDialog(warningDialog())
        }
    }
}