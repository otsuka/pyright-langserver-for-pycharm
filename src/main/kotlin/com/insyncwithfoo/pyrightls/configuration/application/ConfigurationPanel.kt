package com.insyncwithfoo.pyrightls.configuration.application

import com.insyncwithfoo.pyrightls.configuration.Hint
import com.insyncwithfoo.pyrightls.configuration.bindText
import com.insyncwithfoo.pyrightls.configuration.displayPathHint
import com.insyncwithfoo.pyrightls.configuration.executablePathResolvingHint
import com.insyncwithfoo.pyrightls.configuration.onInput
import com.insyncwithfoo.pyrightls.configuration.prefilledWithRandomPlaceholder
import com.insyncwithfoo.pyrightls.configuration.secondColumnPathInput
import com.insyncwithfoo.pyrightls.message
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.Row
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel


private fun relativePathHint() =
    Hint.error(message("configurations.hint.globalMustBeAbsolute"))


private fun Row.makeAlwaysUseGlobalInput(block: Cell<JBCheckBox>.() -> Unit) =
    checkBox(message("configurations.global.alwaysUseGlobal.label")).apply(block)


private fun Row.makeGlobalExecutableInput(block: Cell<TextFieldWithBrowseButton>.() -> Unit) =
    secondColumnPathInput().apply(block)


private fun Row.makeUseEditorFontInput(block: Cell<JBCheckBox>.() -> Unit) =
    checkBox(message("configurations.global.useEditorFont.label")).apply(block)


private fun Row.makeAddTooltipPrefixInput(block: Cell<JBCheckBox>.() -> Unit) =
    checkBox(message("configurations.global.addTooltipPrefix.label")).apply(block)


private fun Row.makeLinkErrorCodesInput(block: Cell<JBCheckBox>.() -> Unit) =
    checkBox(message("configurations.global.linkErrorCodes.label")).apply(block)


private fun Row.makeHoverSupportInput(block: Cell<JBCheckBox>.() -> Unit) =
    checkBox(message("configurations.global.hoverSupport.label")).apply(block)


private fun Row.makeCompletionSupportInput(block: Cell<JBCheckBox>.() -> Unit) =
    checkBox(message("configurations.global.completionSupport.label")).apply(block)


private fun Row.makeGoToDefinitionSupportInput(block: Cell<JBCheckBox>.() -> Unit) =
    checkBox(message("configurations.global.goToDefinitionSupport.label")).apply(block)


@Suppress("DialogTitleCapitalization")
internal fun configurationPanel(state: Configurations) = panel {
    // FIXME: The onInput() callbacks are too deeply nested.
    
    row {
        makeAlwaysUseGlobalInput { bindSelected(state::alwaysUseGlobal) }
    }
    
    row(message("configurations.global.globalExecutable.label")) {
        makeGlobalExecutableInput {
            onInput(::displayPathHint) { path ->
                when {
                    !path.isAbsolute -> relativePathHint()
                    else -> executablePathResolvingHint(path)
                }
            }
            
            prefilledWithRandomPlaceholder()
            bindText(state::globalExecutable)
        }
    }
    
    group(message("configurations.global.group.tooltips")) {
        row {
            makeUseEditorFontInput { bindSelected(state::useEditorFont) }
        }
        row {
            makeAddTooltipPrefixInput { bindSelected(state::addTooltipPrefix) }
        }
        row {
            makeLinkErrorCodesInput { bindSelected(state::linkErrorCodes) }
        }
    }
    
    group(message("configurations.global.group.languageServer")) {
        row {
            makeHoverSupportInput { bindSelected(state::hoverSupport) }
            makeCompletionSupportInput { bindSelected(state::completionSupport) }
            makeGoToDefinitionSupportInput { bindSelected(state::goToDefinitionSupport) }
        }
    }
    
}
