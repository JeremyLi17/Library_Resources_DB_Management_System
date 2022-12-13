package com.realdb.finalproject.entity.invoice;

import com.realdb.finalproject.exception.domain.InvoiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author jeremy on 2022/12/13
 */
@Service
public class InvoiceService {
    private final InvoiceRepo invoiceRepo;

    @Autowired
    public InvoiceService(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    public Invoice findInvoiceById(Long id) throws InvoiceNotFoundException {
        Optional<Invoice> invoiceOpt = invoiceRepo.findById(id);
        if (invoiceOpt.isEmpty()) {
            throw new InvoiceNotFoundException("Invoice with id: " + id + " not found");
        }
        return invoiceOpt.get();
    }
}
