<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Code changelog


This page documents code changes.
For user-facing changes, see [`CHANGELOG.md`][_-1].


  [_-1]: ./CHANGELOG.md


## [Unreleased]

### Added

* Global options "Use editor font" and "Prefix tooltips" are added. (9ebda455)
* Test are added. (HEAD)

### Fixed

* A `panel.apply()` call is added to
  [`PyrightLSConfigurable.isModified()`][6-1].
  This ensures that the state of panel is synchronized
  before being compared with the original state,
  which was not the case in v0.1.0-poc.3. (9ebda455)


  [6-1]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/master/src/main/kotlin/com/insyncwithfoo/pyrightls/configuration/PyrightLSConfigurable.kt


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


  [3-1]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/master/src/main/kotlin/com/insyncwithfoo/pyrightls/configuration/PathResolvingHint.kt
  [3-2]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/master/.github/workflows/build.yaml
  [3-3]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/master/.scripts/
  [3-4]: https://plugins.gradle.org/plugin/org.jetbrains.qodana
  [3-5]: https://github.com/JetBrains/qodana-action
  [3-6]: https://github.com/JetBrains/intellij-platform-gradle-plugin
  [3-7]: https://plugins.jetbrains.com/docs/intellij/kotlin-ui-dsl-version-2.html
  [3-8]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/blob/master/src/main/kotlin/com/insyncwithfoo/pyrightls/configuration/PyrightLSConfigurable.kt
  


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


  [2-1]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/tree/master/src/main/resources/icons
  [2-2]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/tree/master/CHANGELOG.md


## [0.1.0-poc.1] - 2024-03-17

### Added

* Project initialized.


  [Unreleased]: https://github.com/InSyncWithFoo/pyright-for-pycharm/compare/v0.1.0-poc.3..HEAD
  [0.1.0-poc.3]: https://github.com/InSyncWithFoo/pyright-for-pycharm/compare/v0.1.0-poc.2..v0.1.0-poc.3
  [0.1.0-poc.2]: https://github.com/InSyncWithFoo/pyright-for-pycharm/compare/v0.1.0-poc.1..v0.1.0-poc.2
  [0.1.0-poc.1]: https://github.com/InSyncWithFoo/pyright-langserver-for-pycharm/commits
