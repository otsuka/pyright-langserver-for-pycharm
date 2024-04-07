<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Code changelog


This page documents code changes.
For user-facing changes, see [`CHANGELOG.md`][_-1].


  [_-1]: ./CHANGELOG.md


## [Unreleased]

<i>This section is currently empty.</i>


## [0.1.0-poc.4] - 2024-04-07

### Added

* Global options "Use editor font" and "Prefix tooltips" are added. (9ebda455)
* Tests are added. (0ba420d7)

### Changed

* [`pluginIcon.svg`][4-1] is resized to 40 by 40 to comply with
  [the Approval Guidelines][4-2]. (96d974b5)
* [`README.md`][4-3] is rewritten to alter the plugin description. (801c9546)
* Bug reports and feature requests now have automatic assignees. (801c9546)
* [`build.yaml`][4-4] now runs tests on all three platforms.
  (801c9546, 616e6363)
* Publicly-facing classes [`SupportProvider`][4-5] and [`Descriptor`][4-6]
  are renamed. (7c96ada8)

### Fixed

* A `panel.apply()` call is added to
  [`PyrightLSConfigurable.isModified()`][4-7].
  This ensures that the state of panel is synchronized
  before being compared with the original state,
  which was not the case in v0.1.0-poc.3. (9ebda455)


  [4-1]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/96d974b5/src/main/resources/META-INF/pluginIcon.svg
  [4-2]: https://plugins.jetbrains.com/legal/approval-guidelines
  [4-3]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/801c9546/README.md
  [4-4]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/616e6363/.github/workflows/build.yaml
  [4-5]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/7c96ada8/src/main/kotlin/com/insyncwithfoo/pyrightls/server/PyrightLSSupportProvider.kt
  [4-6]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/7c96ada8/src/main/kotlin/com/insyncwithfoo/pyrightls/server/PyrightLSDescriptor.kt
  [4-7]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/9ebda455/src/main/kotlin/com/insyncwithfoo/pyrightls/configuration/PyrightLSConfigurable.kt


## [0.1.0-poc.3] - 2024-03-31

### Added

* [`PathResolvingHint.kt`][3-1] is added. (a31ced27)

### Changed

* The [`build.yaml`][3-2] workflow now:
  * Edits old releases when the changelogs are changed, and
  * Uploads corresponding artifacts as new drafts are created.
  
  The two helper Python scripts are added under [`.scripts`][3-3].
  (ca944192)

* Plugin verifier results are now always uploaded. (ca944192)
* [The Qodana Gradle plugin][3-4] and its corresponding action
  [@JetBrains/qodana-action][3-5] are updated to 2023.3.2. (474b797c)
* [The IntelliJ Platform Gradle plugin][3-6] is updated to 1.17.3. (5546f8fa)
* UI-related code is rewritten to use [Kotlin UI DSL][3-7]. (a31ced27)
* All APIs are now either internal or private.
  * Some of them no longer have the prefix `PyrightLS` in their names.
  
  (a31ced27)

### Changed

* The `configuration.common` module is removed
  in favor of [`PyrightLSConfigurable.kt`][3-8]. (a31ced27)


  [3-1]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/a31ced27/src/main/kotlin/com/insyncwithfoo/pyrightls/configuration/PathResolvingHint.kt
  [3-2]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/ca944192/.github/workflows/build.yaml
  [3-3]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/ca944192/.scripts/
  [3-4]: https://plugins.gradle.org/plugin/org.jetbrains.qodana
  [3-5]: https://github.com/JetBrains/qodana-action
  [3-6]: https://github.com/JetBrains/intellij-platform-gradle-plugin
  [3-7]: https://plugins.jetbrains.com/docs/intellij/kotlin-ui-dsl-version-2.html
  [3-8]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/a31ced27/src/main/kotlin/com/insyncwithfoo/pyrightls/configuration/PyrightLSConfigurable.kt


## [0.1.0-poc.2] - 2024-03-24

### Added

* Project option "Auto-suggest executable" is added. (6dffdaa6)
* [Icons][2-1] are added. (6dffdaa6)
* Messages are merged into one single bundle. (6dffdaa6)
* The "Always use global" option is moved to
  the second column of the second row. (6dffdaa6)
* `PyrightLSAllConfigurations` is added. (6dffdaa6)

### Changed

* Project is renamed. (f821150f)
* Configuration constructs are now marked internal. (6dffdaa6)
* [`CHANGELOG.md`][2-2] is rewritten and
  `CHANGELOG_CODE.md` is added. (6dffdaa6)
* Gradle is updated to 8.7. (4ad34a46)


  [2-1]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/tree/6dffdaa6/src/main/resources/icons
  [2-2]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/tree/6dffdaa6/CHANGELOG.md


## [0.1.0-poc.1] - 2024-03-17

### Added

* Project initialized.


  [Unreleased]: https://github.com/InSyncWithFoo/pyright-for-pycharm/compare/v0.1.0-poc.4..HEAD
  [0.1.0-poc.4]: https://github.com/InSyncWithFoo/pyright-for-pycharm/compare/v0.1.0-poc.3..v0.1.0-poc.4
  [0.1.0-poc.3]: https://github.com/InSyncWithFoo/pyright-for-pycharm/compare/v0.1.0-poc.2..v0.1.0-poc.3
  [0.1.0-poc.2]: https://github.com/InSyncWithFoo/pyright-for-pycharm/compare/v0.1.0-poc.1..v0.1.0-poc.2
  [0.1.0-poc.1]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/commits
