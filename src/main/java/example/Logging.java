package example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Logging.class);

        for(int i = 0; i < 10; i++) {
            logger.info("인포!");
        }
    }
}
