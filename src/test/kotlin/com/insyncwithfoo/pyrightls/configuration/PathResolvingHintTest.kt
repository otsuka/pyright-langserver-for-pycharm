package com.insyncwithfoo.pyrightls.configuration

import junit.framework.TestCase
import java.nio.file.Path
import kotlin.io.path.div


class PathResolvingHintTest : TestCase() {
    
    private val cwd = Path.of(".")
    
    fun `test executablePathResolvingHint - file`() {
        val path = cwd / Path.of("gradlew")
        val hint = executablePathResolvingHint(path)
        
        assertTrue(hint.icon === HintIcon.Success)
    }
    
    fun `test executablePathResolvingHint - symlink`() {
        val path = cwd / ".idea" / "icon.svg"
        val hint = executablePathResolvingHint(path)
        
        assertTrue(hint.icon === HintIcon.Success)
    }
    
    fun `test executablePathResolvingHint - directory`() {
        val path = cwd / "gradle"
        val hint = executablePathResolvingHint(path)
        
        assertTrue(hint.icon === HintIcon.Error)
    }
    
}
