package com.realdb.finalproject.entity.copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jeremy on 2022/12/11
 */
@Service
public class CopyService {

    private final CopyRepo copyRepo;

    @Autowired
    public CopyService(CopyRepo copyRepo) {
        this.copyRepo = copyRepo;
    }

    public Copy addCopy(Copy copy) {
        copyRepo.save(copy);
        return copy;
    }

    public List<Copy> findAllCopiesByBookName(String bookName) {
        return copyRepo.findAllCopiesByBookName(bookName);
    }

    public void deleteCopy(Integer id) {
        copyRepo.deleteById(id);
    }
}
