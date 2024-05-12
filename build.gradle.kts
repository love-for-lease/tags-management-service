plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.lease-for-love"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
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
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named("build") {
	dependsOn("archTest", "componentTest")
}