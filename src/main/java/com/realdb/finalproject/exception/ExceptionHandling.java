package com.realdb.finalproject.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.exception.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

/**
 * @author jeremy on 2022/12/8
 */
@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
    public static final String EVENT_NOT_FOUND = "Event not found";
    public static final String SPONSOR_NOT_FOUND = "Sponsor not found";
    public static final String ORGANIZATION_SPONSOR_NOT_FOUND = "Organization Sponsor not found";
    public static final String INDIVIDUAL_SPONSOR_NOT_FOUND = "Individual Sponsor not found";
    public static final String RESERVATION_NOT_FOUND = "Reservation not found";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String RENTAL_NOT_FOUND = "Rental not found";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found";
    public static final String COPY_NOT_FOUND = "Copy not found";
    public static final String SEMINAR_NOT_FOUND = "Seminar not found";
    public static final String EXHIBITION_NOT_FOUND = "Exhibition not found";

    public static final String BOOK_NOT_FOUND = "Book not found";
    public static final String PAYMENT_NOT_FOUND = "Payment not found";
    public static final String AUTHOR_NAME_NOT_FOUND = "Author not found";
    public static final String STUDY_ROOM_NOT_FOUND = "Study room not found";
    private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. "
            + "Please send a '%s' request";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, "
            + "Please contact administration";
    private static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
    private static final String PAGE_NOT_FOUNT_MSG = "There is not mapping for this URL";
    public static final String ERROR_PAGE_PATH = "/error"; // http://localhost:8081/error

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisableException() {
        return createHttpResponse(BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialException() {
        return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> lockedException() {
        return createHttpResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException exception) {
        return createHttpResponse(UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<HttpResponse> emailExistException(EmailExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<HttpResponse> noHandlerFoundException() {
//        return createHttpResponse(BAD_REQUEST, PAGE_NOT_FOUNT_MSG);
//    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<HttpResponse> authorNameNotFoundException() {
        return createHttpResponse(BAD_REQUEST, AUTHOR_NAME_NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<HttpResponse> bookNotFoundException() {
        return createHttpResponse(BAD_REQUEST, BOOK_NOT_FOUND);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<HttpResponse> paymentNotFoundException() {
        return createHttpResponse(BAD_REQUEST, PAYMENT_NOT_FOUND);
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<HttpResponse> rentalNotFoundException() {
        return createHttpResponse(BAD_REQUEST, RENTAL_NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<HttpResponse> customerNotFoundException() {
        return createHttpResponse(BAD_REQUEST, CUSTOMER_NOT_FOUND);
    }

    @ExceptionHandler(CopyNotFoundException.class)
    public ResponseEntity<HttpResponse> copyNotFoundException() {
        return createHttpResponse(BAD_REQUEST, COPY_NOT_FOUND);
    }

    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity<HttpResponse> usernameExistException(UsernameExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(StudyRoomNotFoundException.class)
    public ResponseEntity<HttpResponse> studyRoomNotFoundException() {
        return createHttpResponse(BAD_REQUEST, STUDY_ROOM_NOT_FOUND);
    }

    @ExceptionHandler(SeminarNotFoundException.class)
    public ResponseEntity<HttpResponse> seminarNotFoundException() {
        return createHttpResponse(BAD_REQUEST, SEMINAR_NOT_FOUND);
    }

    @ExceptionHandler(ExhibitionNotFoundException.class)
    public ResponseEntity<HttpResponse> exhibitionNotFoundException() {
        return createHttpResponse(BAD_REQUEST, EXHIBITION_NOT_FOUND);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<HttpResponse> eventNotFoundException() {
        return createHttpResponse(BAD_REQUEST, EVENT_NOT_FOUND);
    }

    @ExceptionHandler(SponsorNotFoundException.class)
    public ResponseEntity<HttpResponse> sponsorNotFoundException() {
        return createHttpResponse(BAD_REQUEST, SPONSOR_NOT_FOUND);
    }

    @ExceptionHandler(OrganizationSponsorNotFoundException.class)
    public ResponseEntity<HttpResponse> organizationSponsorNotFoundException() {
        return createHttpResponse(BAD_REQUEST, ORGANIZATION_SPONSOR_NOT_FOUND);
    }

    @ExceptionHandler(IndividualSponsorNotFoundException.class)
    public ResponseEntity<HttpResponse> individualSponsorNotFoundException() {
        return createHttpResponse(BAD_REQUEST, INDIVIDUAL_SPONSOR_NOT_FOUND);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<HttpResponse> reservationNotFoundException() {
        return createHttpResponse(BAD_REQUEST, RESERVATION_NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(
                exception.getSupportedHttpMethods().iterator().next()
        );

        return createHttpResponse(METHOD_NOT_ALLOWED,
                String.format(METHOD_IS_NOT_ALLOWED, supportedMethod)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        logger.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
        logger.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> iOException(IOException exception) {
        logger.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(
                httpStatus.value(),
                httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(),
                message.toUpperCase()), httpStatus);
    }

    @RequestMapping(ERROR_PAGE_PATH)
    public ResponseEntity<HttpResponse> notFound404() {
        return createHttpResponse(NOT_FOUND, PAGE_NOT_FOUNT_MSG);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PAGE_PATH;
    }
}
