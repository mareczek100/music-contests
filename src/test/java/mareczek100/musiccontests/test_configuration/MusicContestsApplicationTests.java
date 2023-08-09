package mareczek100.musiccontests.test_configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mareczek100.musiccontests.MusicContestsApplication;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Import(PersistenceTestConfig.class)
@SpringBootTest(
		classes = {MusicContestsApplication.class},
		properties = "spring.flyway.clean-disabled=false",
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AllArgsConstructor
@NoArgsConstructor
abstract class MusicContestsApplicationTests {

	private Flyway flyway;
	@BeforeEach
	void setUp() {
		Assertions.assertNotNull(flyway);
		flyway.clean();
		flyway.migrate();
	}
	@Test
	void contextLoads() {
	}

}
