package ve.com.cantv.common.hera.logging;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Liodegar on 10/21/2018.
 */
public class HeraLoggerFactoryTest {

    @Test
    public void getLogger() {
        HeraLogger heraLogger = HeraLoggerFactory.getLogger("myClass");
        Assert.assertNotNull(heraLogger);

        HeraLogger heraLogger2 = HeraLoggerFactory.getLogger("myClass");
        Assert.assertSame(heraLogger, heraLogger2);
    }
}
