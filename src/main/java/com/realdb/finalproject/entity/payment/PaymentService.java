package com.realdb.finalproject.entity.payment;

import com.realdb.finalproject.entity.invoice.Invoice;
import com.realdb.finalproject.entity.invoice.InvoiceRepo;
import com.realdb.finalproject.exception.domain.InvoiceNotFoundException;
import com.realdb.finalproject.exception.domain.PaymentNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/11
 */
@Service
public class PaymentService {
    private final PaymentRepo paymentRepo;
    private final InvoiceRepo invoiceRepo;

    @Autowired
    public PaymentService(PaymentRepo paymentRepo, InvoiceRepo invoiceRepo) {
        this.paymentRepo = paymentRepo;
        this.invoiceRepo = invoiceRepo;
    }

    public void makePayment(BigDecimal amount, String method, String fullName, Long id)
            throws InvoiceNotFoundException {
        Optional<Invoice> invoiceOpt = invoiceRepo.findById(id);
        if (invoiceOpt.isEmpty()) {
            throw new InvoiceNotFoundException("Invoice with id:" + id + " not found");
        }

        Payment payment = new Payment();
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentAmount(amount);
        payment.setMethod(method);
        payment.setCardHolderFullName(fullName);
        payment.setInvoice(invoiceOpt.get());
        paymentRepo.save(payment);
    }

    public void deletePayment(Integer id) {
        paymentRepo.deleteById(id);
    }

    public List<Payment> findPaymentByInvoiceId(Integer invoiceId) {
        List<Payment> payments = paymentRepo.findPaymentByInvoiceId(invoiceId);
        return payments;
    }

    @Transactional
    public void updatePayment(Integer id,
                              String newMethod,
                              String newCardHolderName,
                              BigDecimal newAmount,
                              LocalDate newDate) throws PaymentNotFoundException {
        Optional<Payment> paymentOpt = paymentRepo.findById(id);
        if (paymentOpt.isEmpty()) {
            throw new PaymentNotFoundException("Payment with id: " + id + " not found");
        }

        Payment payment = paymentOpt.get();
        if (StringUtils.isNotBlank(newMethod) && !newMethod.equals(payment.getMethod())) {
            payment.setMethod(newMethod);
        }
        if (StringUtils.isNotBlank(newCardHolderName)
                && !newCardHolderName.equals(payment.getCardHolderFullName())) {
            payment.setMethod(newCardHolderName);
        }
        if (newAmount != null && !newAmount.equals(payment.getPaymentAmount())) {
            payment.setPaymentAmount(newAmount);
        }
        if (newDate != null && !newDate.equals(payment.getPaymentDate())) {
            payment.setPaymentDate(newDate);
        }
        paymentRepo.save(payment);
    }

    public BigDecimal getTotalPaymentById(Integer id) {
        return paymentRepo.getTotalPaymentById(id);
    }
}
