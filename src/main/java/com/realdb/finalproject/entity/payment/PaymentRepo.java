package com.realdb.finalproject.entity.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author jeremy on 2022/12/11
 */
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

    @Query("SELECT p FROM Payment p WHERE Payment.invoice.id = :invoiceId")
    List<Payment> findPaymentByInvoiceId(@Param("invoiceId") Integer invoiceId);
}
