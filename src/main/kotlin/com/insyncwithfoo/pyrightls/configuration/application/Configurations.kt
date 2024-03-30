package com.insyncwithfoo.pyrightls.configuration.application

import com.intellij.openapi.components.BaseState


internal class Configurations : BaseState() {
    var alwaysUseGlobal by property(false)
    var globalExecutable by string(null)
}
