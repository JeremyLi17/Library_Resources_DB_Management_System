package com.realdb.finalproject.entity.author;

import com.realdb.finalproject.exception.domain.AuthorNotFoundException;
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

    public Author getAuthor(String lastName, String firstName) throws AuthorNotFoundException {
        Optional<Author> authorOpt = authorRepo.findAuthorByLastNameAndFirstName(lastName, firstName);
        if (authorOpt.isEmpty()) {
            throw new AuthorNotFoundException("Author " + firstName + " " + lastName + " not found");
        }
        return authorOpt.get();
    }

    public Author saveAuthor(String email,
                           String firstName,
                           String lastName,
                           String street,
                           String city,
                           String country,
                           String zipcode) {
        Author author = new Author();
        author.setEmail(email);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setCity(city);
        author.setStreet(street);
        author.setCountry(country);
        author.setZipcode(zipcode);
        authorRepo.save(author);
        return author;
    }

    public void deleteAuthor(Integer id) throws AuthorNotFoundException {
        boolean exists = authorRepo.existsById(id);
        if (!exists) {
            throw new AuthorNotFoundException("Author with id " + id + " does not exists");
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
                && !Objects.equals(author.getFirstName(), fName)) {
            author.setFirstName(fName);
        }

        if (lName != null && lName.length() > 0
                && !Objects.equals(author.getLastName(), lName)) {
            author.setLastName(lName);
        }

        if (country != null && country.length() > 0
                && !Objects.equals(author.getCountry(), country)) {
            author.setCountry(country);
        }

        if (street != null && street.length() > 0
                && !Objects.equals(author.getStreet(), street)) {
            author.setStreet(street);
        }

        if (city != null && city.length() > 0
                && !Objects.equals(author.getCity(), city)) {
            author.setCity(city);
        }

        if (zipcode != null && !Objects.equals(author.getZipcode(), zipcode)) {
            author.setZipcode(zipcode);
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
