import org.gradle.api.Plugin
import org.gradle.api.Project

// default service plugin
class ProtoServicePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.apply(plugin: 'org.springframework.boot')
        project.apply(plugin: "io.spring.dependency-management")

        project.dependencyManagement {
            imports {
                mavenBom "org.springframework.cloud:spring-cloud-dependencies:${project.ext.releaseTrainVersion}"
            }
        }

        project.configurations.all {
            exclude group: 'org.apache.logging.log4j'
            exclude group: 'log4j'
        }

        project.dependencies {
            compile "org.springframework.cloud:spring-cloud-starter-sleuth"
            compile "org.springframework.cloud:spring-cloud-starter-zipkin"
        }

    }
}
