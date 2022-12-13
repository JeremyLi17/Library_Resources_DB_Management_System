package com.realdb.finalproject.entity.Rental;

import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.customer.CustomerRepo;
import com.realdb.finalproject.entity.copy.Copy;
import com.realdb.finalproject.entity.copy.CopyRepo;
import com.realdb.finalproject.exception.domain.CopyNotFoundException;
import com.realdb.finalproject.exception.domain.CustomerNotFoundException;
import com.realdb.finalproject.exception.domain.RentalNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/11
 */
@Service
@AllArgsConstructor
public class RentalService {
    private final RentalRepo rentalRepo;
    private final CustomerRepo customerRepo;
    private final CopyRepo copyRepo;

    public List<Rental> getAllRentalByCustomerId(Integer customerId) {
        return rentalRepo.getAllRentalByCustomerId(customerId);
    }

    public void deleteRental(Long id) throws RentalNotFoundException {
        Optional<Rental> rentalOpt = rentalRepo.findById(id);
        if (rentalOpt.isEmpty()) {
            throw new RentalNotFoundException("Rental with id: " + id + " not found");
        }
        rentalOpt.get().getCopy().setCopyStatus("Y");
        // mark as return
        Rental rental = rentalOpt.get();
        rental.setStatus("R");
        rental.setActReturnDate(LocalDate.now());
        rentalRepo.save(rental);
    }

    public void addRental(Integer copyId, Integer customerId, LocalDate expReturnDate)
            throws CustomerNotFoundException, CopyNotFoundException {
        Rental rental = new Rental();
        Optional<Customer> customerOpt = customerRepo.findById(customerId);
        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException("Customer with id:" + customerId + " not found");
        }
        rental.setCustomer(customerOpt.get());
        Optional<Copy> copyOpt = copyRepo.findById(copyId);
        if (copyOpt.isEmpty()) {
            throw new CopyNotFoundException("Copy with id:" + copyId + " not found");
        }
        rental.setCopy(copyOpt.get());
        copyOpt.get().setCopyStatus("N");

        rental.setBorrowDate(LocalDate.now());
        rental.setStatus("B");
        rental.setExpReturnDate(expReturnDate);

        rentalRepo.save(rental);
    }

    @Transactional
    public void updateRental(Long id, Integer customerId, Integer copyId)
            throws CustomerNotFoundException, CopyNotFoundException, RentalNotFoundException {
        Optional<Rental> rentalOpt = rentalRepo.findById(id);
        if (rentalOpt.isEmpty()) {
            throw new RentalNotFoundException("Rental with id: " + id + " not found");
        }
        Rental rental = rentalOpt.get();
        if (customerId != null && !rental.getCustomer().getId().equals(customerId)) {
            Optional<Customer> customerOpt = customerRepo.findById(customerId);
            if (customerOpt.isEmpty()) {
                throw new CustomerNotFoundException("Customer with id:" + customerId + " not found");
            }
            rental.setCustomer(customerOpt.get());
        }
        if (copyId != null && !rental.getCopy().getId().equals(copyId)) {
            Optional<Copy> copyOpt = copyRepo.findById(copyId);
            if (copyOpt.isEmpty()) {
                throw new CopyNotFoundException("Copy with id:" + copyId + " not found");
            }
            rental.setCopy(copyOpt.get());
        }
        rentalRepo.save(rental);
    }
}
