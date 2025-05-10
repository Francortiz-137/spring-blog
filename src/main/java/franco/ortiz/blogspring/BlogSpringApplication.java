package franco.ortiz.blogspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BlogSpringApplication {
    private static final Logger logger = LoggerFactory.getLogger(BlogSpringApplication.class);
    public static void main(String[] args) {
        logger.info("Starting the application");

        SpringApplication.run(BlogSpringApplication.class, args);
    }

}
