package com.insyncwithfoo.pyrightls

import java.nio.file.InvalidPathException
import java.nio.file.Path


internal fun String.toPathOrNull() =
    try {
        Path.of(this)
    } catch (_: InvalidPathException) {
        null
    }


internal val Path.isEmpty: Boolean
    get() = this.toString() == ""


internal fun Path.resolvedAgainst(base: Path?) =
    base?.resolve(this) ?: this
