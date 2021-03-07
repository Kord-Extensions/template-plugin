# KordEx Plugin Template

This repository contains a basic KordEx example plugin for you to use as a template for your own KordEx plugins. This
includes the following:

- A basic plugin with a standard configuration, loading a basic extension that allows you to slap other people, using
  both chat commands and slash commands.

- A Gradle Kotlin build script that uses the KordEx Gradle plugin and Detekt for linting (with a
  fairly strict configuration) – this uses Gradle 7's new version catalogue feature, for easy configuration of
  dependencies.
- GitHub CI scripts that build the plugin and publish its artefacts.
- A reasonable `.gitignore` file, including one in the `.idea` folder that ignores files that you shouldn't commit –
  if you're using IDEA yourself, you should install the Ignore plugin to handle changes to this for you.

When built, your plugin `.zip` file will be generated within `build/distributions/`.
Drop this file into the `plugins/` folder alongside your bot, and it will load the plugin automatically.

**Note:** This template includes a `.editorconfig` file that defaults to using tabs for indentation in almost all file
types. This is because tabs are more accessible for the blind, or those with impaired vision. We won't accept
feedback or PRs targeting this approach, though you can always change it in your projects.

## Potential Changes

- The `.yml` files in `.github/` are used to configure GitHub apps. If you're not using them, you can remove them.
- The provided `LICENSE` file contains The Unlicense, which makes this repository public domain. You will probably want
  to change this—we suggest looking at [Choose a License](https://choosealicense.com/) if you're not sure where to
  start.
- In the `build.gradle.kts`:
  - Set the `group` and `version` properties as appropriate.
  - In the `kordEx` block, update your plugin's configuration as appropriate.
- In the `settings.gradle.kts`, update the name of the root project as appropriate.
- The bundled Detekt config is pretty strict — you can check over `detekt.yml` if you want to change it, but you need to
  follow the to-dos in that file regardless.

## Further Reading

To learn more about KordEx and how to work with it, [please look at the documentation site](https://docs.kordex.dev).

For more information on the KordEx Gradle plugin and what you can do with it,
[please look at the plugin documentation](https://docs.kordex.dev/kordex-plugin.html).
