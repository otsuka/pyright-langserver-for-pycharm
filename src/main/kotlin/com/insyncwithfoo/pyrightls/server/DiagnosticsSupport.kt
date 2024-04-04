package com.insyncwithfoo.pyrightls.server

import com.insyncwithfoo.pyrightls.pyrightLSConfigurations
import com.intellij.openapi.editor.ex.util.EditorUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.text.HtmlChunk
import com.intellij.platform.lsp.api.customization.LspDiagnosticsSupport
import org.eclipse.lsp4j.Diagnostic


private fun <T> T.applyIf(condition: Boolean, block: T.() -> T): T =
    if (condition) block() else this


private fun String.toPreformatted(font: String? = null) =
    HtmlChunk.div()
        .applyIf(font != null) { style("font-family: '$font'") }
        .child(HtmlChunk.text(this)).toString()


@Suppress("UnstableApiUsage")
internal class DiagnosticsSupport(private val project: Project) : LspDiagnosticsSupport() {
    
    override fun getTooltip(diagnostic: Diagnostic): String {
        val configurations = project.pyrightLSConfigurations
        var tooltip = diagnostic.message
        val font = when {
            configurations.useEditorFont -> EditorUtil.getEditorFont()
            else -> null
        }
        
        if (configurations.addTooltipPrefix) {
            tooltip = "Pyright: $tooltip"
        }
        
        return tooltip.toPreformatted(font?.name)
    }
    
}