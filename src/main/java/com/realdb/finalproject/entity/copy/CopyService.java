package com.realdb.finalproject.entity.copy;

import com.realdb.finalproject.entity.book.Book;
import com.realdb.finalproject.entity.book.BookRepo;
import com.realdb.finalproject.exception.domain.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/11
 */
@Service
@Slf4j
public class CopyService {

    private final CopyRepo copyRepo;
    private final BookRepo bookRepo;

    @Autowired
    public CopyService(CopyRepo copyRepo, BookRepo bookRepo) {
        this.copyRepo = copyRepo;
        this.bookRepo = bookRepo;
    }

    public Copy addCopy(String copyStatus, String bookName) throws BookNotFoundException {
        Copy copy = new Copy();
        Optional<Book> bookOpt = bookRepo.findBookByBookName(bookName);
        if (bookOpt.isEmpty()) {
            throw new BookNotFoundException("Book: " + bookName + "not found");
        }
        copy.setBook(bookOpt.get());
        copy.setCopyStatus(copyStatus);
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
