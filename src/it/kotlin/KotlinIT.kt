import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class KotlinIT {

    @Test
    fun `test Kotlin to Java references in same integration test suite source set`() {
        val testing = JavaIntegrationTestFixtureInSameSourceSet() // Does not compile.
        assertNotNull(testing)
    }
}
