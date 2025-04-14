package progressive_overlords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ProgressiveOverlordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgressiveOverlordsApplication.class, args);
	}

}
