package vars.helloWorld
// Import the required stuff: JUnit &amp; Jenkins Pipeline Unit
import org.junit.*
import static org.junit.Assert.*
import com.lesfurets.jenkins.unit.*

// Extend the BasePipelineTest to use the Jenkins Pipeline Unit framework
class ShouldEchoHelloWorld extends BasePipelineTest {
    // The class under test
    def logError

    @Before
    void setUp() {
        super.setUp()

        // Load the script, without executing it.
        logError = loadScript("vars/helloWorld.groovy")
    }

    @Test
    void 'Echo with Hello and default value'() {
        // Execute the 'call' method on our class under test
        logError.call()

        // Validate that echo is only called once
        assertEquals(1, helper.methodCallCount('echo'))

        // Validate that the call to echo contains the string "ERROR"
        assertTrue(helper.getCallStack()[1].args[0].toString() == 'Hello, human.')
    }

}
