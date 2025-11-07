package dev.slne.surf.spoiler.protection.command

import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.spoiler.protection.dialog.warningDialog
import dev.slne.surf.spoiler.protection.plugin
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import dev.slne.surf.surfapi.core.api.messages.pagination.Pagination
import org.bukkit.entity.Player

fun spoilerProtectionCommand() = commandTree("spoiler-protection") {
    withPermission("surf.spoiler.protection.command")

    literalArgument("reload") {
        anyExecutor { executor, _ ->
            plugin.configuration.reload()

            executor.sendText {
                appendPrefix()
                success("Die Spoiler-Warnschutz-Konfiguration wurde neu geladen.")
            }
        }
    }
    literalArgument("add") {
        stringArgument("playerName") {
            anyExecutor { executor, args ->
                val playerName: String by args

                plugin.configuration.edit {
                    players.add(playerName)
                }

                executor.sendText {
                    appendPrefix()
                    success("Der Spoiler-Warnschutz wurde für den Spieler ")
                    variableValue(playerName)
                    success(" hinzugefügt.")
                }
            }
        }
    }

    literalArgument("remove") {
        stringArgument("playerName") {
            anyExecutor { executor, args ->
                val playerName: String by args

                plugin.configuration.edit {
                    players.remove(playerName)
                }

                executor.sendText {
                    appendPrefix()
                    success("Der Spoiler-Warnschutz wurde für den Spieler ")
                    variableValue(playerName)
                    success(" entfernt.")
                }
            }
        }
    }

    literalArgument("list") {
        anyExecutor { executor, _ ->
            val players = plugin.configuration.config.players

            val pagination = Pagination<String> {
                title { primary("Spieler") }

                rowRenderer { row, _ ->
                    listOf(
                        buildText {
                            darkSpacer(">")
                            appendSpace()
                            variableValue(row)
                        }
                    )
                }
            }

            executor.sendText {
                appendPrefix()
                success("Spieler mit Spoiler-Warnschutz:")
                appendNewline()
                append(pagination.renderComponent(players))
            }
        }
    }

    literalArgument("show") {
        entitySelectorArgumentOnePlayer("player") {
            anyExecutor { executor, args ->
                val player: Player by args

                player.showDialog(warningDialog())

                executor.sendText {
                    appendPrefix()
                    success("Der Spoiler-Warnschutz wurde für ")
                    variableValue(player.name)
                    success(" angezeigt.")
                }
            }
        }
    }
}