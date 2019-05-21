package stuffy;

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

import stuffy.Stuffy;
import stuffy.StuffyRepository;

@Controller
@RequestMapping(path = "/stuffys")
public class StuffyController {
	@Autowired
	private StuffyRepository stuffyRepository;

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Stuffy> getAllStuffys() {
		return stuffyRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public @ResponseBody Optional<Stuffy> getStuffy(@PathVariable int id) {
//		Optional<Stuffy> stuffy = stuffyRepository.findById(id);
//		if (stuffy.isPresent()) {
//			
//		}
		return stuffyRepository.findById(id);
	}
	
//	@GetMapping(path = "/add")
//	public @ResponseBody String addNewStuffy(@RequestParam String name, @RequestParam String email) {
//
//		Stuffy n = new Stuffy();
//		n.setName(name);
//		n.setEmail(email);
//		stuffyRepository.save(n);
//		return "Saved";
//	}
	
	@PostMapping("/")
	public @ResponseBody String addStuffy(@RequestBody Stuffy stuffy) {
		stuffyRepository.save(stuffy);
		return "Saved";
	}
	
	@DeleteMapping("/")
	public @ResponseBody String deleteStuffy (@RequestBody Stuffy stuffy) {
		stuffyRepository.delete(stuffy);
		return "Deleted";
	}
	
	@PutMapping("/")
	public @ResponseBody String updateStuffy (@RequestBody Stuffy stuffy) {
		stuffyRepository.save(stuffy);
		return "Saved";
	}
}