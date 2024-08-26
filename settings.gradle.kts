pluginManagement {
	plugins {
		kotlin("jvm") version "2.0.20-Beta1"
		kotlin("plugin.serialization") version "2.0.20"

		// Update this in libs.version.toml when you change it here.
		id("io.gitlab.arturbosch.detekt") version "1.23.6"

		id("dev.kordex.gradle.kordex") version "1.3.0"
	}
}

rootProject.name = "template"
