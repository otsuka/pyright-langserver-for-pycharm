package com.insyncwithfoo.pyrightls.server


internal class Settings(block: Settings.() -> Unit) {
    
    private val python = Python()
    
    init {
        this.apply(block)
    }
    
    fun python(block: Python.() -> Unit) {
        python.apply(block)
    }
    
    internal data class Python(
        var pythonPath: String? = null
    )
    
}
