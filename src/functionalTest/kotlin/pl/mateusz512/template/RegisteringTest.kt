package pl.mateusz512.template

import org.amshove.kluent.`should contain`
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class RegisteringTest {

    @BeforeEach
    fun setup(@TempDir tmpDir: File) { }

    @Test
    fun `should register tasks`(@TempDir tmpDir: File) {
        val buildFile = File(tmpDir, "build.gradle.kts")
        buildFile.applyPlugin()

        buildFile.appendText(
            """
                myExtension {
                    commands {
                        register("abc").configure {
                            message = "abc"
                        }
                        register("def")
                    }
                }
            """.trimIndent()
        )

        val result = GradleRunner.create()
            .withProjectDir(tmpDir)
            .withArguments("tasks")
            .withPluginClasspath()
            .build()

        result.output `should contain` "say-abc"
        result.output `should contain` "say-def"
    }

    @Test
    fun `task should print out a message`(@TempDir tmpDir: File) {
        val buildFile = File(tmpDir, "build.gradle.kts")
        buildFile.applyPlugin()

        buildFile.appendText(
            """
                myExtension {
                    commands {
                        register("abc").configure {
                            message = "hello-world"
                        }
                        register("def")
                    }
                }
            """.trimIndent()
        )

        val result = GradleRunner.create()
            .withProjectDir(tmpDir)
            .withArguments("say-abc")
            .withPluginClasspath()
            .build()

        result.output `should contain` "hello-world"
    }
}

private fun File.applyPlugin() {
    appendText(
        """
        plugins {
            id("configurable-plugin-template")
        }
        """.trimIndent()
    )
}
