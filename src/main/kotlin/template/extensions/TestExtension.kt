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
import dev.kordex.core.i18n.withContext
import dev.kordex.core.utils.respond
import template.i18n.Translations

class TestExtension : Extension() {
	override val name = "test"

	override suspend fun setup() {
		chatCommand(::SlapArgs) {
			name = Translations.Commands.Slap.name
			description = Translations.Commands.Slap.description

			check { failIf(event.message.author == null) }

			action {
				// Don't slap ourselves on request, slap the requester!
				val realTarget = if (arguments.target.id == event.kord.selfId) {
					message.author!!
				} else {
					arguments.target
				}

				message.respond(
					Translations.Commands.Slap.action
						.withContext(this)
						.translate(realTarget.mention, arguments.weapon)
				)
			}
		}

		chatCommand {
			name = Translations.Commands.Button.name
			description = Translations.Commands.Button.description

			check { failIf(event.message.author == null) }

			action {
				message.respond {
					components {
						publicButton {
							label = Translations.Commands.Button.label
								.withLocale(getLocale())

							action {
								respond {
									content = Translations.Commands.Button.action
										.withLocale(getLocale())
										.translate()
								}
							}
						}
					}
				}
			}
		}

		publicSlashCommand(::SlapSlashArgs) {
			name = Translations.Commands.Slap.name
			description = Translations.Commands.Slap.description

			action {
				// Don't slap ourselves on request, slap the requester!
				val realTarget = if (arguments.target.id == event.kord.selfId) {
					user
				} else {
					arguments.target
				}

				respond {
					content = Translations.Commands.Slap.action
						.withContext(this@action)
						.translate(realTarget.mention, arguments.weapon)
				}
			}
		}

		publicSlashCommand {
			name = Translations.Commands.Button.name
			description = Translations.Commands.Button.description

			action {
				respond {
					components {
						publicButton {
							label = Translations.Commands.Button.label
								.withLocale(getLocale())

							action {
								respond {
									content = Translations.Commands.Button.action
										.withLocale(getLocale())
										.translate()
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
			name = Translations.Commands.Slap.Args.Target.name
			description = Translations.Commands.Slap.Args.Target.description
		}

		val weapon by coalescingDefaultingString {
			name = Translations.Commands.Slap.Args.Weapon.name

			defaultValue = "🐡"
			description = Translations.Commands.Slap.Args.Weapon.description
		}
	}

	inner class SlapSlashArgs : Arguments() {
		val target by user {
			name = Translations.Commands.Slap.Args.Target.name
			description = Translations.Commands.Slap.Args.Target.description
		}

		// Slash commands don't support coalescing strings
		val weapon by defaultingString {
			name = Translations.Commands.Slap.Args.Weapon.name

			defaultValue = "🐡"
			description = Translations.Commands.Slap.Args.Weapon.description
		}
	}
}
