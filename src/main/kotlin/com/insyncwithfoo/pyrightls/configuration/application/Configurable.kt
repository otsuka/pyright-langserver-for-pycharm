package com.insyncwithfoo.pyrightls.configuration.application

import com.insyncwithfoo.pyrightls.configuration.PyrightLSConfigurable
import com.insyncwithfoo.pyrightls.message


internal class Configurable : PyrightLSConfigurable<Configurations>() {
    
    override val service = ConfigurationService.getInstance()
    override val state = service.state.copy()
    
    override val panel by lazy { configurationPanel(state) }
    
    override fun getDisplayName() = message("configurations.global.displayName")
    
}
