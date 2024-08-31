package template.extensions

import dev.kordex.core.commands.Arguments
import dev.kordex.core.commands.converters.impl.coalescingDefaultingString
import dev.kordex.core.commands.converters.impl.defaultingString
import dev.kordex.core.commands.converters.impl.user
import dev.kordex.core.components.components
import dev.kordex.core.components.publicButton
import dev.kordex.core.extensions.Extension
import dev.kordex.core.extensions.chatCommand
import dev.kordex.core.extensions.publicSlashCommand
import dev.kordex.core.utils.respond
import template.TRANSLATION_BUNDLE

class TestExtension : Extension() {
	override val name = "test"
	override val bundle: String = TRANSLATION_BUNDLE

	override suspend fun setup() {
		chatCommand(::SlapArgs) {
			name = "commands.slap.name"
			description = "commands.slap.description"

			check { failIf(event.message.author == null) }

			action {
				// Don't slap ourselves on request, slap the requester!
				val realTarget = if (arguments.target.id == event.kord.selfId) {
					message.author!!
				} else {
					arguments.target
				}

				message.respond(
					"_${translate("commands.slap.action", arrayOf(realTarget.mention, arguments.weapon))}_"
				)
			}
		}

		chatCommand {
			name = "commands.button.name"
			description = "commands.button.description"

			check { failIf(event.message.author == null) }

			action {
				message.respond {
					components {
						publicButton {
							bundle = TRANSLATION_BUNDLE
							label = translate("commands.button.label")

							action {
								respond {
									content = translate("commands.button.action")
								}
							}
						}
					}
				}
			}
		}

		publicSlashCommand(::SlapSlashArgs) {
			name = "commands.slap.name"
			description = "commands.slap.description"

			action {
				// Don't slap ourselves on request, slap the requester!
				val realTarget = if (arguments.target.id == event.kord.selfId) {
					user
				} else {
					arguments.target
				}

				respond {
					content = "_${translate("commands.slap.action", arrayOf(realTarget.mention, arguments.weapon))}_"
				}
			}
		}

		publicSlashCommand {
			name = "commands.button.name"
			description = "commands.button.description"

			action {
				respond {
					components {
						publicButton {
							bundle = TRANSLATION_BUNDLE
							label = translate("commands.button.label")

							action {
								respond {
									content = translate("commands.button.action")
								}
							}
						}
					}
				}
			}
		}
	}

	inner class SlapArgs : Arguments() {
		val target by user {
			name = "commands.slap.args.target.name"
			description = "commands.slap.args.target.description"
		}

		val weapon by coalescingDefaultingString {
			name = "commands.slap.args.weapon.name"

			defaultValue = "🐡"
			description = "commands.slap.args.weapon.description"
		}
	}

	inner class SlapSlashArgs : Arguments() {
		val target by user {
			name = "commands.slap.args.target.name"
			description = "commands.slap.args.target.description"
		}

		// Slash commands don't support coalescing strings
		val weapon by defaultingString {
			name = "commands.slap.args.weapon.name"

			defaultValue = "🐡"
			description = "commands.slap.args.weapon.description"
		}
	}
}
