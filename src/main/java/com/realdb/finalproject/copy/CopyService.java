package com.realdb.finalproject.copy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.realdb.finalproject.entity.Copy;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CopyService {
    private final CopyRepo copyRepo;

    public List<Copy> getCopies() {
        return copyRepo.findAll();
    }
    public void addCopy(Copy copy){
        copyRepo.save(copy);
    }
    public void deleteCopy(Integer id){

        copyRepo.deleteById(id);
    }
    public Optional<Copy> getCopybyid(Integer id){
        return copyRepo.findById(id);

    }
}
