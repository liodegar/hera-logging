/**
 * Copyright 2004 CANTV. All rights reserved.
 * CANTV PROPRIETARY/CONFIDENTIAL. Use is subject to license
 * terms.
 * <p>
 * This software is the confidential and proprietary information
 * of CANTV. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered
 * into with CANTV.
 * <p>
 * Creation date: 2004-10-20
 */
package ve.com.cantv.common.hera.logging;

import java.util.HashMap;
import java.util.Map;

/**
 * Class HeraLoggerFactory
 * This is the factory to create new instances of HeraLogger.
 * The creation of a HeraLogger also creates the wrapped object (an implementation of UniversalLogger)
 * to which the method calls are delegated.
 *
 * @author Liodegar Bracamonte
 */
public class HeraLoggerFactory {

    /**
     * The default logging class name.
     */
    private static final String DEFAULT_LOGGING_CLASS_NAME = "ve.com.cantv.common.hera.logging.StdoutLogger";

    /**
     * Map to stores the HeraLogger objects
     */
    private static Map<String, HeraLogger> loggers = new HashMap<>();

    /**
     * To avoid the instantiation outside this class
     */
    private HeraLoggerFactory() {
    }

    /**
     * Gets a HeraLogger objects using a logger name
     *
     * @param name The HeraLogger's name
     * @return HeraLogger A HeraLogger object
     */
    public static synchronized HeraLogger getLogger(String name) {
        return loggers.computeIfAbsent(name, key -> new HeraLogger(createUniversalLogger(key)));
    }


    /**
     * Returns a UniversalLogger object using a class name defined in cantv.properties
     * This method is intended to be used to create any UniversalLogger object.
     *
     * @param name The logger name
     * @return UniversalLogger If className cannot be retrieved from hera-logging.properties, returns a StdoutLogger instance,
     * otherwise creates a new UniversalLogger instance using a reflection mechanism. If during the reflection process
     * occur any exception a StdoutLogger instance is returned.
     */
    private static UniversalLogger createUniversalLogger(String name) {
        String className = LoggingConfigurator.getLoggingClassName();
        try {
            if (className == null || DEFAULT_LOGGING_CLASS_NAME.equals(className)) {
                return new StdoutLogger(name);
            }
            Class<UniversalLogger> clazz = (Class<UniversalLogger>) Class.forName(className);
            return clazz.getConstructor(new Class[]{String.class}).newInstance(name);

        } catch (Exception e) {
            System.out.println("HeraLoggerFactory.createUniversalLogger. UniversalLogger cannot be created in successfully way. Error creating the class='" + className + "'");
            System.out.println("HeraLoggerFactory.createUniversalLogger StdoutLogger will be returned");
            e.printStackTrace();
            return new StdoutLogger(name);
        }
    }
}
