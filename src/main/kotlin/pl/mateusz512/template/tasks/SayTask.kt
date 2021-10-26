package pl.mateusz512.template.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import pl.mateusz512.template.ext.Command


abstract class SayTask: DefaultTask() {

    @get:Input
    abstract val command: Property<Command>

    @TaskAction
    fun run() {
        command.get().let {
            logger.lifecycle("The message from ${it.name} is: ${it.message}")
        }
    }
}