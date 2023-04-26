import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class KotlinTest {

    @Test
    fun `test Kotlin to Java references in same unit test suite source set`() {
        val testing = JavaUnitTestFixtureInSameSourceSet() // Compiles.
        assertNotNull(testing)
    }
}
