package com.insyncwithfoo.pyrightls.configuration

import com.intellij.openapi.project.Project
import com.insyncwithfoo.pyrightls.configuration.application.ConfigurationService as ApplicationConfigurationService
import com.insyncwithfoo.pyrightls.configuration.project.ConfigurationService as ProjectConfigurationService


internal class ConfigurationService private constructor(
    applicationService: ApplicationConfigurationService,
    val projectService: ProjectConfigurationService
) {
    
    val state = applicationService.state mergeWith projectService.state
    
    companion object {
        fun getInstance(project: Project): ConfigurationService {
            val applicationService = ApplicationConfigurationService.getInstance()
            val projectService = ProjectConfigurationService.getInstance(project)
            
            return ConfigurationService(applicationService, projectService)
        }
    }
    
}
