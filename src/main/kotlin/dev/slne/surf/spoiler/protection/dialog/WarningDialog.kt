@file:Suppress("UnstableApiUsage")

package dev.slne.surf.spoiler.protection.dialog

import dev.slne.surf.surfapi.bukkit.api.dialog.base
import dev.slne.surf.surfapi.bukkit.api.dialog.dialog
import dev.slne.surf.surfapi.bukkit.api.dialog.type
import dev.slne.surf.surfapi.core.api.font.toSmallCaps
import dev.slne.surf.surfapi.core.api.messages.CommonComponents
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.potion.PotionEffectType

fun warningDialog() = dialog {
    base {
        title {
            primary("SPOILER SCHUTZ".toSmallCaps(), TextDecoration.BOLD)
        }

        canCloseWithEscape = false

        body {
            plainMessage(300) {
                error(
                    "Achtung! Du bist dabei, Inhalte zu sehen, die Spoiler enthalten können. Bist du sicher, dass du fortfahren möchtest?",
                    TextDecoration.BOLD
                )
            }
        }
    }

    type {
        confirmation {
            yes {
                label {
                    success("Fortfahren")
                }

                action {
                    playerCallback {
                        it.removePotionEffect(PotionEffectType.BLINDNESS)
                        it.closeDialog()
                    }
                }
            }

            no {
                label {
                    error("Abbrechen")
                }

                action {
                    playerCallback {
                        it.removePotionEffect(PotionEffectType.BLINDNESS)
                        it.kick(buildText {
                            appendDisconnectMessage("DU WURDEST VOM SERVER GEWORFEN", {
                                variableValue("Du hast den Spoiler Schutz abgelehnt.")
                            }, {
                                append(CommonComponents.ISSUE_FOOTER)
                            })
                        })
                    }
                }
            }
        }
    }
}