package com.insyncwithfoo.pyrightls.server

import com.insyncwithfoo.pyrightls.message
import com.insyncwithfoo.pyrightls.path
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import java.nio.file.Path


@Suppress("UnstableApiUsage")
internal class PyrightLSDescriptor(
    project: Project,
    private val executable: Path
) : ProjectWideLspServerDescriptor(project, PRESENTABLE_NAME) {
    
    override fun isSupportedFile(file: VirtualFile) = file.extension == "py"
    
    override fun createCommandLine() =
        GeneralCommandLine(executable.toString(), "--stdio").apply {
            withWorkDirectory(project.path?.toString())
            withCharset(Charsets.UTF_8)
        }
    
    override val lspServerListener = Listener(project)
    override val lspDiagnosticsSupport = DiagnosticsSupport(project)
    
    companion object {
        val PRESENTABLE_NAME = message("languageServer.representableName")
    }
    
}
