package com.realdb.finalproject.relation.customerExhibition;

import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.entity.event.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jeremy on 2022/12/11
 */
@Repository
public interface CustomerExhibitionRepo
        extends JpaRepository<CustomerExhibition, CustomerExhibitionId> {

    @Query("SELECT e FROM Exhibition e WHERE e.id IN " +
            "(SELECT ce.exhibitionEvent.id FROM CustomerExhibition ce " +
            "WHERE ce.customer.id = ?1)")
    List<Exhibition> getByCustomer(Integer id);

    @Query("SELECT c FROM Customer c WHERE c.id IN " +
            "(SELECT ce.customer.id FROM CustomerExhibition ce " +
            "WHERE ce.exhibitionEvent.id = ?1)")
    List<Customer> getByExhibition(Integer id);
}
