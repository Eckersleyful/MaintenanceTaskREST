package etteplan.rest;

import etteplan.rest.device.models.Device;
import org.junit.jupiter.api.Test;

public class MaintenanceTaskTest {



    @Test
    public void testCompareTo(){

        Device mockDevice = new Device("testDevice", 1997, "202");
        /*


        MaintenanceTask laterMockTask = new MaintenanceTask(1, mockDevice, Severity.CRITICAL, new Date(System.currentTimeMillis()), "A test task", true);

        MaintenanceTask earlierMockTask = new MaintenanceTask(1, mockDevice, Severity.UNIMPORTANT, new Date(123415), "A test task", true);

        Assertions.assertEquals(1, laterMockTask.compare(laterMockTask, earlierMockTask), "Comparing a critical task to unimportant");


        earlierMockTask.setSeverity(Severity.CRITICAL);
        Assertions.assertEquals(1, laterMockTask.compare(laterMockTask, earlierMockTask), "Comparing two equally important task with different registration times");
        Assertions.assertEquals(-1, laterMockTask.compare(earlierMockTask, laterMockTask), "Comparing two equally important task with different registration times");*/
    }

}