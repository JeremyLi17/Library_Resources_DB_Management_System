package com.realdb.finalproject.entity.Rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jeremy on 2022/12/11
 */
@Repository
public interface RentalRepo extends JpaRepository<Rental, Long> {

    @Transactional
    @Query("SELECT r FROM Rental r WHERE r.customer.id = :id")
    List<Rental> getAllRentalByCustomerId(@Param("id") Integer id);
}
