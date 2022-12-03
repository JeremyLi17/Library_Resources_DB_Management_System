package com.realdb.finalproject.copy;
import com.realdb.finalproject.entity.Copy;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/copy")
@AllArgsConstructor
public class CopyController {
    private final CopyService copyService;
    @GetMapping("/all")
    public List<Copy> getCopies() {
        return copyService.getCopies();
    }
    @GetMapping()
    public Optional<Copy> getCopybyID(@RequestBody Integer id){
        return copyService.getCopybyid(id);
    }
    @PostMapping()
    public void addCopy(@RequestBody Copy copy){
        copyService.addCopy(copy);
    }
    @DeleteMapping()
    private void deleteCopy(@RequestParam Integer id){
        copyService.deleteCopy(id);
    }



}
