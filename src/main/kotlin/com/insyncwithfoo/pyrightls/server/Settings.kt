package com.insyncwithfoo.pyrightls.server


class Analysis {
    var logLevel: String? = null
}


internal class Python {
    
    private val analysis = Analysis()
    
    var pythonPath: String? = null
    
    fun analysis(block: Analysis.() -> Unit) {
        analysis.apply(block)
    }
    
}


internal class Settings(block: Settings.() -> Unit) {
    
    private val python = Python()
    
    init {
        this.apply(block)
    }
    
    fun python(block: Python.() -> Unit) {
        python.apply(block)
    }
    
}
