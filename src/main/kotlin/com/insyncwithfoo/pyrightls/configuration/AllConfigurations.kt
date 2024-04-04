package com.insyncwithfoo.pyrightls.configuration

import org.jetbrains.annotations.SystemDependent
import com.insyncwithfoo.pyrightls.configuration.application.Configurations as ApplicationConfigurations
import com.insyncwithfoo.pyrightls.configuration.project.Configurations as ProjectConfigurations


internal infix fun ApplicationConfigurations.mergeWith(other: ProjectConfigurations) =
    AllConfigurations(
        alwaysUseGlobal = this.alwaysUseGlobal,
        globalExecutable = this.globalExecutable,
        useEditorFont = this.useEditorFont,
        addTooltipPrefix = this.addTooltipPrefix,
        
        projectExecutable = other.projectExecutable,
        autoSuggestExecutable = other.autoSuggestExecutable
    )


internal data class AllConfigurations(
    val alwaysUseGlobal: Boolean,
    val globalExecutable: @SystemDependent String?,
    val useEditorFont: Boolean,
    val addTooltipPrefix: Boolean,
    
    val projectExecutable: @SystemDependent String?,
    val autoSuggestExecutable: Boolean
) {
    val executable: @SystemDependent String?
        get() = when {
            alwaysUseGlobal -> globalExecutable
            else -> projectExecutable ?: globalExecutable
        }
}
