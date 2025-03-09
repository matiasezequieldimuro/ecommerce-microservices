package mdimuro.ecommerce.microservices.inventory_service.infraestructure.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalLogger {

    private static final Logger logger = LoggerFactory.getLogger("GlobalLogger");

    public static Logger getInstance() {
        return logger;
    }
}
