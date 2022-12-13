package com.realdb.finalproject.entity.payment;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.exception.domain.PaymentNotFoundException;
import com.realdb.finalproject.utility.BuildResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author jeremy on 2022/12/11
 */
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    public static final String DELETED_SUCCESSFULLY = "Payment deleted successfully";

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
        paymentService.makePayment(payment.getPaymentAmount(), payment.getMethod(), payment.getCardHolderFullName());
        return new ResponseEntity<>(payment, CREATED);
    }

    @GetMapping("/totalPaymentAmount/{invoiceId}")
    public ResponseEntity<BigDecimal> getTotalPaymentById(@PathVariable("invoiceId") Integer id) {
        BigDecimal totalPayment = paymentService.getTotalPaymentById(id);
        return new ResponseEntity<>(totalPayment, OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deletePayment(@RequestParam("id") Integer id) {
        paymentService.deletePayment(id);
        return BuildResponse.build(NO_CONTENT, DELETED_SUCCESSFULLY);
    }

    @GetMapping("/list/{invoiceId}")
    public ResponseEntity<List<Payment>> findPaymentByInvoiceId(
            @PathVariable("invoiceId") Integer invoiceId) {
        List<Payment> payments = paymentService.findPaymentByInvoiceId(invoiceId);
        return new ResponseEntity<>(payments, OK);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updatePayment(
            @RequestParam("id") Integer id,
            @RequestParam(value = "method", required = false) String method,
            @RequestParam(value = "cardHolderName", required = false) String cardHolderName,
            @RequestParam(value = "amount", required = false) BigDecimal amount,
            @RequestParam(value = "paymentDate",required = false) LocalDate date)
            throws PaymentNotFoundException {
        paymentService.updatePayment(id, method, cardHolderName, amount, date);
        return BuildResponse.build(ACCEPTED, "Update payment successfully");
    }
}
