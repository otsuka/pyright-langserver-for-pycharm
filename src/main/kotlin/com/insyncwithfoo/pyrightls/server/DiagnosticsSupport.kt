package com.insyncwithfoo.pyrightls.server

import com.insyncwithfoo.pyrightls.HighlightSeverity
import com.insyncwithfoo.pyrightls.PyrightLSInspection
import com.insyncwithfoo.pyrightls.pyrightLSConfigurations
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.ex.util.EditorUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.text.HtmlChunk
import com.intellij.platform.lsp.api.customization.LspDiagnosticsSupport
import com.intellij.profile.codeInspection.InspectionProjectProfileManager
import org.eclipse.lsp4j.Diagnostic
import org.eclipse.lsp4j.DiagnosticSeverity


private fun <T> T.applyIf(condition: Boolean, block: T.() -> T): T =
    if (condition) block() else this


private fun String.toPreformatted(font: String? = null) =
    HtmlChunk.div()
        .applyIf(font != null) { style("font-family: '$font'") }
        .child(HtmlChunk.text(this)).toString()


private val Diagnostic.suffixedMessage: String
    get() {
        val code = code?.get() as String?
        val suffix = if (code != null) " ($code)" else ""
        
        return "$message$suffix"
    }


private fun Project.getPyrightLSInspection(): PyrightLSInspection {
    val inspectionManager = InspectionProjectProfileManager.getInstance(this)
    val profile = inspectionManager.currentProfile
    
    return profile.getInspectionTool(PyrightLSInspection.SHORT_NAME, this)!!.tool as PyrightLSInspection
}


@Suppress("UnstableApiUsage")
internal class DiagnosticsSupport(private val project: Project) : LspDiagnosticsSupport() {
    
    override fun getHighlightSeverity(diagnostic: Diagnostic): HighlightSeverity? {
        val inspection = project.getPyrightLSInspection()
        
        return when (diagnostic.severity) {
            DiagnosticSeverity.Error -> HighlightSeverity(inspection.highlightSeverityForErrors)
            DiagnosticSeverity.Warning -> HighlightSeverity(inspection.highlightSeverityForWarnings)
            DiagnosticSeverity.Information -> HighlightSeverity(inspection.highlightSeverityForInformation)
            DiagnosticSeverity.Hint -> super.getHighlightSeverity(diagnostic)
            else -> throw RuntimeException("This should not happen")
        }
    }
    
    override fun getMessage(diagnostic: Diagnostic) = diagnostic.suffixedMessage
    
    override fun getTooltip(diagnostic: Diagnostic): String {
        val configurations = project.pyrightLSConfigurations
        var tooltip = diagnostic.suffixedMessage
        
        if (configurations.addTooltipPrefix) {
            tooltip = "Pyright: $tooltip"
        }
        
        val font = when {
            configurations.useEditorFont -> EditorUtil.getEditorFont()
            else -> null
        }
        
        return tooltip.toPreformatted(font?.name)
    }
    
}
