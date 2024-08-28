import dev.kordex.gradle.plugins.kordex.DataCollection

plugins {
	kotlin("jvm")
	kotlin("plugin.serialization")

	id("io.gitlab.arturbosch.detekt")
	id("dev.kordex.gradle.kordex")
}

group = "template"
version = "1.0"

dependencies {
	detektPlugins(libs.detekt)

	// Use compileOnly instead of implementation, otherwise it'll be bundled with the plugin
	compileOnly(libs.kotlin.stdlib)
	compileOnly(libs.kx.ser)
	compileOnly(libs.logging)
}

kordEx {
	kordExVersion = "2.2.0-SNAPSHOT"

	plugin {
		id = "kordex-template-plugin"
		pluginClass = "template.plugins.TemplatePlugin"
		version = project.version.toString()  // Note: Cannot be a snapshot or otherwise contain a dash (-) character.

		author = "Jane Doe"
		description = "Example plugin from the template repository."
		license = "Unlicense"

		kordExVersion(">= 2.2.0") // KordEx 2.2.0 or later
	}
}

detekt {
	buildUponDefaultConfig = true

	config.from(rootProject.files("detekt.yml"))
}
