pluginManagement {
	plugins {
		kotlin("jvm") version "2.0.21"
		kotlin("plugin.serialization") version "2.0.21"

		// Update this in libs.version.toml when you change it here.
		id("io.gitlab.arturbosch.detekt") version "1.23.6"

		id("dev.kordex.gradle.kordex") version "1.5.1"
	}

	repositories {
		gradlePluginPortal()
		mavenCentral()

		maven("https://snapshots-repo.kordex.dev")
		maven("https://releases-repo.kordex.dev")
	}
}

rootProject.name = "template"
