package com.insyncwithfoo.pyrightls.server


internal data class Analysis(
    var logLevel: String? = null,
    var autoImportCompletions: Boolean? = null,
    var diagnosticMode: String? = null,
    var autoSearchPaths: Boolean = true
)


internal data class Python(
    var pythonPath: String? = null,
    val analysis: Analysis = Analysis()
)


internal data class Pyright(
    var disableTaggedHints: Boolean? = null
)


internal data class Settings(
    val python: Python = Python(),
    val pyright: Pyright = Pyright()
)
