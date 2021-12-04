plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
    // Other dependencies.
    testImplementation(kotlin("test"))
}


tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }
    test {
        useJUnitPlatform()
    }
    wrapper {
        gradleVersion = "7.3"
    }
}
