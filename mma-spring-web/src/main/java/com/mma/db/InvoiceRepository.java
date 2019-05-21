package com.mma.db;

import org.springframework.data.repository.CrudRepository;

import com.mma.business.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {

}
