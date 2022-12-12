package com.realdb.finalproject.entity.copy;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.utility.BuildResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author jeremy on 2022/12/11
 */
@RestController
@RequestMapping("/api/copy")
public class CopyController {
    public static final String DELETED_SUCCESSFULLY = "Copy deleted successfully";
    private final CopyService copyService;

    @Autowired
    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }

    @PostMapping("/add")
    public ResponseEntity<Copy> addCopy(@RequestBody Copy copy) {
        Copy newCopy = copyService.addCopy(copy);
        return new ResponseEntity<>(newCopy, CREATED);
    }

    @GetMapping("/list/{bookName}")
    public ResponseEntity<List<Copy>> findAllCopiesByBookName(
            @PathVariable("bookName") String bookName) {
        List<Copy> copies = copyService.findAllCopiesByBookName(bookName);
        return new ResponseEntity<>(copies, OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteCopy(@RequestParam("id") Integer id) {
        copyService.deleteCopy(id);
        return BuildResponse.build(NO_CONTENT, DELETED_SUCCESSFULLY);
    }
}
