plugins {
    kotlin("jvm") version "1.5.10"
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

group = "pl.mateusz512"
version = "1.0-SNAPSHOT"

val functionalTest: SourceSet by sourceSets.creating

gradlePlugin {
    plugins {
        create("configurablePluginTemplate") {
            id = "configurable-plugin-template"
            implementationClass = "pl.mateusz512.template.ConfigurablePlugin"
        }
    }
    testSourceSets(functionalTest)
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.amshove.kluent:kluent:1.65")

    "functionalTestImplementation"("org.junit.jupiter:junit-jupiter-api:5.7.1")
    "functionalTestRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine")
    "functionalTestImplementation"("org.amshove.kluent:kluent:1.65")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform {  }
}

val functionalTestTask = tasks.register<Test>("functionalTest") {
    group = "verification"
    testClassesDirs = functionalTest.output.classesDirs
    classpath = functionalTest.runtimeClasspath
    useJUnitPlatform()
}

tasks.check {
    dependsOn(functionalTestTask)
}

