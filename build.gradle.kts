
group = "com.lease-for-love"
version = "0.0.1-SNAPSHOT"
val sourceSets = the<SourceSetContainer>()
val basePackage = "com.leaseforlove.tagsmanagementservice"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.openapi.generator") version "7.5.0"
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

// Open Api CodeGen
tasks.compileJava { dependsOn("openApiGenerate") }
openApiGenerate {
	generatorName.set("spring")
	inputSpec.set("$rootDir/src/main/resources/static/api-docs.yaml")
	outputDir.set("$buildDir/generated/openapi")
	modelNameSuffix.set("Dto")
	configOptions.set(
			mapOf(
					"dateLibrary" to "java8",
					"gradleBuildFile" to "false",
					"basePackage" to "$basePackage.application.web.api",
					"apiPackage" to "$basePackage.application.web.api",
					"modelPackage" to "$basePackage.application.web.dto",
					"interfaceOnly" to "true",
					"hideGenerationTimestamp" to "true",
					"openApiNullable" to "false",
					"useTags" to "true",
					"useJakartaEe" to "true"
			)
	)
}
sourceSets { getByName("main") { java { srcDir("$buildDir/generated/openapi/src/main/java") } } }

repositories {
	jcenter()
	mavenCentral()
}


// Component Test
sourceSets {
	create("componentTest") {
		compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
		runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
	}
}
val componenetTestTask = tasks.create("componentTest", Test::class) {
	description = "Runs the component tests."
	group = "verification"

	testClassesDirs = sourceSets["componentTest"].output.classesDirs
	classpath = sourceSets["componentTest"].runtimeClasspath
}
val componentTestImplementation: Configuration by configurations.getting {
	extendsFrom(configurations.implementation.get())
	extendsFrom(configurations.testImplementation.get())
}
configurations["componentTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

// Arch Test
sourceSets {
	create("archTest") {
		compileClasspath += sourceSets.main.get().output
		runtimeClasspath += sourceSets.main.get().output
	}
}
val archTest = tasks.create("archTest", Test::class) {
	description = "Runs the architecture tests."
	group = "verification"

	testClassesDirs = sourceSets["archTest"].output.classesDirs
	classpath = sourceSets["archTest"].runtimeClasspath
}

val archTestImplementation: Configuration by configurations.getting {
	extendsFrom(configurations.implementation.get())
	extendsFrom(configurations.testImplementation.get())
}
configurations["archTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web") {
		modules {
			module("org.springframework.boot:spring-boot-starter-tomcat") {
				replacedBy("org.springframework.boot:spring-boot-starter-jetty")
			}
		}
	}
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")

	// Testing
	archTestImplementation("com.tngtech.archunit:archunit:1.3.0")
	archTestImplementation("com.tngtech.archunit:archunit-junit5:1.3.0")
	componentTestImplementation("com.github.tomakehurst:wiremock:3.0.0")
	componentTestImplementation("io.rest-assured:rest-assured:5.4.0")
	componentTestImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.13.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Swagger
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.20")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named("build") {
	dependsOn("archTest", "componentTest")
}