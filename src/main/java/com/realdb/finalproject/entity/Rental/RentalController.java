package com.realdb.finalproject.entity.Rental;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.exception.domain.CopyNotFoundException;
import com.realdb.finalproject.exception.domain.CustomerNotFoundException;
import com.realdb.finalproject.exception.domain.RentalNotFoundException;
import com.realdb.finalproject.utility.BuildResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author jeremy on 2022/12/11
 */
@RestController
@RequestMapping("/api/rental")
@AllArgsConstructor
public class RentalController {
    public static final String DELETE_SUCCESSFULLY = "Rental delete successfully";
    public static final String UPDATE_SUCCESSFULLY = "Rental update successfully";
    private final RentalService rentalService;

    @GetMapping("/list/{customerId}")
    public ResponseEntity<List<Rental>> getAllRentalByCustomerId
            (@PathVariable("customerId") Integer customerId) {
        List<Rental> rentals = rentalService.getAllRentalByCustomerId(customerId);
        return new ResponseEntity<>(rentals, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Rental> addRental(@RequestBody Rental rental)
            throws CustomerNotFoundException, CopyNotFoundException {
        rentalService.addRental(rental.getCopy().getId(), rental.getCustomer().getId(),
                rental.getExpReturnDate());
        return new ResponseEntity<>(rental, CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteRental(@RequestParam("id") Long id)
            throws RentalNotFoundException {
        rentalService.deleteRental(id);
        return BuildResponse.build(NO_CONTENT, DELETE_SUCCESSFULLY);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updateRental(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "customerId", required = false) Integer customerId,
            @RequestParam(value = "copyId", required = false) Integer copyId)
            throws RentalNotFoundException, CopyNotFoundException, CustomerNotFoundException {
        rentalService.updateRental(id, customerId, copyId);
        return BuildResponse.build(NO_CONTENT, UPDATE_SUCCESSFULLY);
    }
}
