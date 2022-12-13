package com.realdb.finalproject.entity.invoice;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author jeremy on 2022/12/13
 */
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
}
