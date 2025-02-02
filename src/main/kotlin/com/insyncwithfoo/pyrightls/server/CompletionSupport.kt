package com.insyncwithfoo.pyrightls.server

import com.google.gson.JsonObject
import com.insyncwithfoo.pyrightls.pyrightLSConfigurations
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.customization.LspCompletionSupport
import org.eclipse.lsp4j.CompletionItem
import org.eclipse.lsp4j.CompletionItemKind
import org.eclipse.lsp4j.InsertTextFormat
import org.eclipse.lsp4j.TextEdit
import kotlin.math.min


private const val CARET_POSITION = "\$0"

private const val doubleQuote = "\""
private const val singleQuote = "'"
private const val tripleDoubleQuote = "\"\"\""
private const val tripleSingleQuote = "'''"


private val quoteSequences =
    listOf(tripleDoubleQuote, doubleQuote, tripleSingleQuote, singleQuote)


private val CompletionItem.isCallable: Boolean
    get() = kind in listOf(
        CompletionItemKind.Method,
        CompletionItemKind.Function,
        CompletionItemKind.Constructor
    )


private val CompletionItem.isAutoImportCompletion: Boolean
    get() {
        val data = this.data
        return data is JsonObject && data.has("autoImportText")
    }


private val CompletionItem.isQuoted: Boolean
    get() = quoteSequence != null


private val CompletionItem.quoteSequence: String?
    get() = quoteSequences.find { label.startsWith(it) && label.endsWith(it) }


private val CompletionParameters.followingCharacters: CharSequence
    get() {
        val documentCharsSequence = editor.document.charsSequence
        val upperBound = min(offset + 2, documentCharsSequence.length - 1)
        
        return documentCharsSequence.slice(offset..upperBound)
    }


private fun CompletionParameters.itemMightTriggerTrailingQuoteBug(item: CompletionItem): Boolean {
    return item.isQuoted && followingCharacters.startsWith(item.quoteSequence!!)
}


private fun CompletionItem.completeWithParentheses() {
    val newInsertText = "$label($CARET_POSITION)"
    
    insertTextFormat = InsertTextFormat.Snippet
    
    when (val textEdit = this.textEdit?.get()) {
        null -> insertText = newInsertText
        is TextEdit -> textEdit.newText = newInsertText
        // InsertReplaceEdit must not be messed with.
    }
}


private fun CompletionItem.useSourceAsDetailIfPossible() {
    // https://github.com/microsoft/pyright/blob/0b7860b/packages/pyright-internal/src/languageService/completionProvider.ts#L932-L934
    detail = labelDetails?.description ?: ""
}


private fun CompletionItem.removeTrailingQuoteSequence() {
    val trailingQuoteSequence = quoteSequence!!
    
    insertText = label.removeSuffix(trailingQuoteSequence)
    
    (textEdit.get() as? TextEdit)?.apply {
        newText = newText.removeSuffix(trailingQuoteSequence)
    }
}


@Suppress("UnstableApiUsage")
internal class CompletionSupport(project: Project) : LspCompletionSupport() {
    
    private val configurations = project.pyrightLSConfigurations
    
    override fun createLookupElement(parameters: CompletionParameters, item: CompletionItem): LookupElement? {
        if (item.isCallable && configurations.autocompleteParentheses) {
            item.completeWithParentheses()
        }
        
        if (item.isAutoImportCompletion && configurations.monkeypatchAutoImportDetails) {
            item.useSourceAsDetailIfPossible()
        }
        
        if (parameters.itemMightTriggerTrailingQuoteBug(item) && configurations.monkeypatchTrailingQuoteBug) {
            item.removeTrailingQuoteSequence()
        }
        
        return super.createLookupElement(parameters, item)
    }
    
}
