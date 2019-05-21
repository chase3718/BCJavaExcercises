package hello;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.User;
import hello.UserRepository;

@Controller
@RequestMapping(path = "/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public @ResponseBody Optional<User> getUser(@PathVariable int id) {
//		Optional<User> user = userRepository.findById(id);
//		if (user.isPresent()) {
//			
//		}
		return userRepository.findById(id);
	}
	
//	@GetMapping(path = "/add")
//	public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
//
//		User n = new User();
//		n.setName(name);
//		n.setEmail(email);
//		userRepository.save(n);
//		return "Saved";
//	}
	
	@PostMapping("/")
	public @ResponseBody String addUser(@RequestBody User user) {
		userRepository.save(user);
		return "Saved";
	}
	
	@DeleteMapping("/")
	public @ResponseBody String deleteUser (@RequestBody User user) {
		userRepository.delete(user);
		return "Deleted";
	}
	
	@PutMapping("/")
	public @ResponseBody String updateUser (@RequestBody User user) {
		userRepository.save(user);
		return "Saved";
	}
}