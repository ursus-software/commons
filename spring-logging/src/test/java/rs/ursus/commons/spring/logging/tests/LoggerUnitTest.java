package rs.ursus.commons.spring.logging.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rs.ursus.commons.spring.logging.TestApplication;
import rs.ursus.commons.spring.logging.annotations.Log;

import static org.junit.Assert.assertNotNull;

/**
 * Created by nighthawk on 5/31/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestApplication.class)
public class LoggerUnitTest {

    @Log
    private Logger log;

    @Test
    public void testWiring() throws Exception {
        assertNotNull("Variable log should not be null.", log);
    }
}
