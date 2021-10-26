plugins {
    id("configurable-plugin-template")
}

myExtension {
    commands {
        register("xyz").configure {
            message = "abc"
        }
    }
}