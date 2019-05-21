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
import com.mma.business.Invoice;
import com.mma.db.InvoiceRepository;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
	@Autowired
	private InvoiceRepository invoiceRepository;

	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(invoiceRepository.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<Invoice> p = invoiceRepository.findById(id);
			if (p.isPresent()) {
				jr = JsonResponse.getInstance(invoiceRepository.findById(id));
			} else {
				jr = JsonResponse.getInstance("No invoice found for id: " + id);
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;

	}

	@PostMapping("/")
	public JsonResponse add(@RequestBody Invoice invoice) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(invoiceRepository.save(invoice));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@DeleteMapping("/")
	public JsonResponse delete(@RequestBody Invoice invoice) {
		JsonResponse jr = null;
		try {
			if (invoiceRepository.existsById(invoice.getId())) {
				invoiceRepository.delete(invoice);
				jr = JsonResponse.getInstance("Invoice deleted");				
			} else {
				jr = JsonResponse.getInstance("No such Invoice: " + invoice);
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/")
	public JsonResponse update(@RequestBody Invoice invoice) {
		JsonResponse jr = null;
		try {
			if (invoiceRepository.existsById(invoice.getId())) {
				jr = JsonResponse.getInstance(invoiceRepository.save(invoice));				
			} else {
				jr = JsonResponse.getInstance("No such Invoice for id: " + invoice.getId());
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
}