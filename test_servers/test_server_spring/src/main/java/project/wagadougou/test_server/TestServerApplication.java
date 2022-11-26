package project.wagadougou.test_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.utils.classes.ResultType;

@SpringBootApplication
@RestController
public class TestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestServerApplication.class, args);
	}
	@GetMapping("/")
	public ResultType<String> hello_world(@RequestParam(name = "name", defaultValue = "World") String name){
		return new ResultType<String>("ok", "message", "Hello " + name);
	}
}
