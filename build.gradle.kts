plugins {
    kotlin("jvm") version "1.8.21"
    id("jvm-test-suite")
}

@Suppress("UnstableApiUsage")
testing {
    suites {
        configureEach {
            if (this is JvmTestSuite) {
                val junitVersion = "5.9.3"
                useJUnitJupiter(junitVersion)
            }
        }

        val test by getting(JvmTestSuite::class)

        register<JvmTestSuite>("integrationTest") {
            dependencies {
                implementation(project())
            }
            sources {
                val sourcesRootDir = "src/it"
                java {
                    setSrcDirs(listOf("$sourcesRootDir/java"))
                }
                kotlin {
                    setSrcDirs(listOf(
                            "$sourcesRootDir/kotlin",
                            "$sourcesRootDir/java",
                    ))
                }
                resources {
                    setSrcDirs(listOf("$sourcesRootDir/resources"))
                }
            }
            targets {
                all {
                    testTask.configure {
                        filter {
                            val testSuffix = "IT"
                            includeTestsMatching("*$testSuffix")
                            // Support JUnit @Nested tests
                            includeTestsMatching("*$testSuffix$*")
                        }
                        shouldRunAfter(test)
                    }
                }
            }
        }
    }
}

tasks {
    named<Wrapper>("wrapper").configure {
        gradleVersion = "8.1.1"
        distributionType = Wrapper.DistributionType.ALL
    }
}
