package pl.mateusz512.template.tasks

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test
import pl.mateusz512.template.ext.Command

class SayTaskTest {

    @Test
    fun testt() {
        val project: Project = ProjectBuilder.builder().build()
        project.pluginManager.apply("configurable-plugin-template")
        project.tasks.register("foo", SayTask::class.java) {
            it.command.set(Command(
                name = "abc"
            ).apply {
                message = "foo-msg"
            })
        }

        project.tasks.getByName("foo").apply {
            this `should be instance of` SayTask::class.java
            (this as SayTask).command.get() `should be equal to`
                    Command(
                        name = "abc"
                    ).apply {
                        message = "foo-msg"
                    }
        }
    }
}