package com.realdb.finalproject.entity.copy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/11
 */
@Repository
public interface CopyRepo extends JpaRepository<Copy, Integer> {

    @Transactional
    @Query("SELECT c FROM Copy c WHERE c.book.bookName = :bookName")
    List<Copy> findAllCopiesByBookName(@Param("bookName") String bookName);
}
