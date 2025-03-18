package mdimuro.ecommerce.microservices.inventory_service.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("test") // Executes class only if profile 'test' is active.
@ExtendWith(SpringExtension.class) // Use Spring functionalities such as dependency injection, load context configuration, etc.
public abstract class TestsMongoDBContainer {

    // Singleton instance of the MongoDB container
    private static final MongoDBContainer mongoDBContainer;

    static {
        // Initialize the container only once.
        // Stop container when JVM shuts down the process.
        mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:5.0.5"));
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
}
