package template.plugins

import dev.kordex.core.plugins.KordExPlugin
import io.github.oshai.kotlinlogging.KotlinLogging
import template.extensions.TestExtension

class TemplatePlugin : KordExPlugin() {
	private val logger = KotlinLogging.logger { }

	override suspend fun setup() {
		logger.info { "Loading test extension..." }

		extension(::TestExtension)
	}
}
