package com.mma.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mma.business.JsonResponse;
import com.mma.business.LineItem;
import com.mma.db.LineItemRepository;

@RestController
@RequestMapping("/lineItems")
public class LineItemController {
	@Autowired
	private LineItemRepository lineItemRepository;

	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(lineItemRepository.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<LineItem> p = lineItemRepository.findById(id);
			if (p.isPresent()) {
				jr = JsonResponse.getInstance(lineItemRepository.findById(id));
			} else {
				jr = JsonResponse.getInstance("No lineItem found for id: " + id);
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;

	}

	@PostMapping("/")
	public JsonResponse add(@RequestBody LineItem lineItem) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(lineItemRepository.save(lineItem));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@DeleteMapping("/")
	public JsonResponse delete(@RequestBody LineItem lineItem) {
		JsonResponse jr = null;
		try {
			if (lineItemRepository.existsById(lineItem.getId())) {
				lineItemRepository.delete(lineItem);
				jr = JsonResponse.getInstance("LineItem deleted");				
			} else {
				jr = JsonResponse.getInstance("No such LineItem: " + lineItem);
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/")
	public JsonResponse update(@RequestBody LineItem lineItem) {
		JsonResponse jr = null;
		try {
			if (lineItemRepository.existsById(lineItem.getId())) {
				jr = JsonResponse.getInstance(lineItemRepository.save(lineItem));				
			} else {
				jr = JsonResponse.getInstance("No such LineItem for id: " + lineItem.getId());
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
}