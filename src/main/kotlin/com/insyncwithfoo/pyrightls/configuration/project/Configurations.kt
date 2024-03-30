package com.insyncwithfoo.pyrightls.configuration.project

import com.intellij.openapi.components.BaseState


internal class Configurations : BaseState() {
    var projectExecutable by string(null)
    var autoSuggestExecutable by property(true)
}
