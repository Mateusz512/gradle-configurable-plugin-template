package pl.mateusz512.template.ext

import org.gradle.api.NamedDomainObjectContainer

interface ConfigurablePluginExtension {
    val commands: NamedDomainObjectContainer<Command>
}

open class Command(
    val name: String,
) {
    var message: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Command) return false

        if (name != other.name) return false
        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        return result
    }
}
