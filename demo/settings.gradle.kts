rootProject.name = "mn"

includeBuild("../micronaut-data"){
    dependencySubstitution {
        substitute(module("io.micronaut.data:micronaut-data-processor")).using(project(":data-processor"))
        substitute(module("io.micronaut.data:micronaut-data-jdbc")).using(project(":data-jdbc"))
    }
}