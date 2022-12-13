package com.realdb.finalproject.entity.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author jeremy on 2022/12/13
 */
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    @Query("SELECT i FROM Invoice i WHERE i.rental.id IN " +
            "(SELECT r.id FROM Rental r WHERE r.customer.id = ?1)")
    List<Invoice> findAllInvoiceByCustomerId(Integer id);
}
