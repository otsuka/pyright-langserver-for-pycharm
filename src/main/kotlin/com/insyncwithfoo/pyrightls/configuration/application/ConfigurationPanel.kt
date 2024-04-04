package com.insyncwithfoo.pyrightls.configuration.application

import com.insyncwithfoo.pyrightls.configuration.Hint
import com.insyncwithfoo.pyrightls.configuration.NO_LABEL
import com.insyncwithfoo.pyrightls.configuration.bindText
import com.insyncwithfoo.pyrightls.configuration.displayPathHint
import com.insyncwithfoo.pyrightls.configuration.executablePathResolvingHint
import com.insyncwithfoo.pyrightls.configuration.makeCellReturnComponent
import com.insyncwithfoo.pyrightls.configuration.onInput
import com.insyncwithfoo.pyrightls.configuration.prefilledWithRandomPlaceholder
import com.insyncwithfoo.pyrightls.configuration.secondColumnPathInput
import com.insyncwithfoo.pyrightls.message
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.Row
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel


private fun relativePathHint() =
    Hint.error(message("configurations.hint.globalMustBeAbsolute"))


private fun Row.makeAlwaysUseGlobalInput() =
    checkBox(message("configurations.global.alwaysUseGlobal.label"))


private fun Row.makeGlobalExecutableInput(block: Cell<TextFieldWithBrowseButton>.() -> Unit) =
    makeCellReturnComponent { secondColumnPathInput().apply(block) }


private fun Row.makeUseEditorFontInput() =
    checkBox(message("configurations.global.useEditorFont.label"))


private fun Row.makeAddTooltipPrefixInput() =
    checkBox(message("configurations.global.addTooltipPrefix.label"))


internal fun configurationPanel(state: Configurations) = panel {
    // FIXME: The onInput() callbacks are too deeply nested.
    
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
    row(NO_LABEL) {
        makeAlwaysUseGlobalInput().bindSelected(state::alwaysUseGlobal)
    }
    
    group(message("configurations.global.group.tooltips")) {
        row {
            makeUseEditorFontInput().bindSelected(state::useEditorFont)
        }
        row {
            makeAddTooltipPrefixInput().bindSelected(state::addTooltipPrefix)
        }
    }
}
