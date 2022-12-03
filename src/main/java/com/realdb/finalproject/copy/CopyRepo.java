package com.realdb.finalproject.copy;
import com.realdb.finalproject.entity.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepo extends JpaRepository<Copy, Integer> {

}
