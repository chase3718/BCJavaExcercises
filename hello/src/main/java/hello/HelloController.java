package hello;

//import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	@ResponseBody
	String home() {
		return "Hello Java Bootcamp!1!!";
	}
	
//	public static void main (String[] args) {
//		SpringApplication.run(HelloController.class, args);
//	}
	
}
