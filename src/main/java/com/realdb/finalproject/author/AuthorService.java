package com.realdb.finalproject.author;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/2
 */
@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public List<Author> getAuthors() {
        return authorRepo.findAll();
    }

    public Optional<Author> getAuthor(Integer id) {
        return authorRepo.findById(id);
    }

    public void saveAuthor(Author author) {
        authorRepo.save(author);
    }

    public void deleteAuthor(Integer id) {
        boolean exists = authorRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "Author with id " + id + " does not exists");
        }
        authorRepo.deleteById(id);
    }

    @Transactional
    public void updateAuthor(Integer id,
                             String country,
                             String zipcode,
                             String fName,
                             String lName,
                             String street,
                             String city,
                             String email) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Author with id " + id + " does not exist"
                ));

        if (fName != null && fName.length() > 0
                && !Objects.equals(author.getAFname(), fName)) {
            author.setAFname(fName);
        }

        if (lName != null && lName.length() > 0
                && !Objects.equals(author.getALname(), lName)) {
            author.setALname(lName);
        }

        if (country != null && country.length() > 0
                && !Objects.equals(author.getACountry(), country)) {
            author.setACountry(country);
        }

        if (street != null && street.length() > 0
                && !Objects.equals(author.getAStreet(), street)) {
            author.setAStreet(street);
        }

        if (city != null && city.length() > 0
                && !Objects.equals(author.getACity(), city)) {
            author.setACity(city);
        }

        if (zipcode != null && !Objects.equals(author.getAZipcode(), zipcode)) {
            author.setAZipcode(zipcode);
        }

        if (email != null && email.length() > 0
                && !Objects.equals(author.getEmail(), email)) {
            Optional<Author> studentOptional = authorRepo.findAuthorByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            author.setEmail(email);
        }
    }
}
