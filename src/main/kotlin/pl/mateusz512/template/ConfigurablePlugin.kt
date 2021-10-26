package pl.mateusz512.template

import org.gradle.api.Plugin
import org.gradle.api.Project
import pl.mateusz512.template.ext.ConfigurablePluginExtension
import pl.mateusz512.template.tasks.SayTask

class ConfigurablePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val sqsExtension = project.extensions.create("myExtension", ConfigurablePluginExtension::class.java)
        sqsExtension.commands.whenObjectAdded { command ->
            project.tasks.register("say-${command.name}", SayTask::class.java).configure {
                it.group = "my-group"
                it.command.set(command)
            }
        }
    }
}