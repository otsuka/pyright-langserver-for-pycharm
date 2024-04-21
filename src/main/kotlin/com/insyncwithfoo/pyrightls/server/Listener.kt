package com.insyncwithfoo.pyrightls.server

import com.insyncwithfoo.pyrightls.pyrightLSConfigurations
import com.insyncwithfoo.pyrightls.sdkPath
import com.intellij.openapi.project.Project
import com.intellij.platform.lsp.api.LspServerListener
import com.intellij.platform.lsp.api.LspServerManager
import org.eclipse.lsp4j.DidChangeConfigurationParams
import org.eclipse.lsp4j.InitializeResult


private fun Project.createPyrightLSSettingsObject() = Settings {
    val configurations = pyrightLSConfigurations
    
    python {
        pythonPath = sdkPath?.toString()
        
        analysis {
            logLevel = configurations.logLevel.label
        }
    }
    
    pyright {
        disableTaggedHints = !configurations.taggedHints
    }
}


@Suppress("UnstableApiUsage")
internal class Listener(val project: Project) : LspServerListener {
    
    override fun serverInitialized(params: InitializeResult) {
        val lspServerManager = LspServerManager.getInstance(project)
        val settings = project.createPyrightLSSettingsObject()
        val parameters = DidChangeConfigurationParams(settings)
        
        lspServerManager.getServersForProvider(PyrightLSSupportProvider::class.java).forEach { lspServer ->
            lspServer.sendNotification {
                it.workspaceService.didChangeConfiguration(parameters)
            }
        }
    }
    
}
