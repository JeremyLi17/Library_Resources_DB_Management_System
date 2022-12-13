package com.realdb.finalproject.entity.invoice;

import com.realdb.finalproject.exception.domain.InvoiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author jeremy on 2022/12/13
 */
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Invoice> findInvoiceById(@PathVariable("id") Long id)
            throws InvoiceNotFoundException {
        Invoice invoice = invoiceService.findInvoiceById(id);
        return new ResponseEntity<>(invoice, OK);
    }

     @GetMapping("/list/{id}")
    public ResponseEntity<List<Invoice>> findAllInvoiceByCustomerId(@PathVariable("id") Long id)
            throws InvoiceNotFoundException {
        List<Invoice> invoices = invoiceService.findAllInvoiceByCustomerId(id);
        return new ResponseEntity<>(invoices, OK);
    }
}
