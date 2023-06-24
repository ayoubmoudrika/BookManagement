package com.example.bookmanagement.user;

import com.example.bookmanagement.Book.Book;
import com.example.bookmanagement.loan.Loan;
import com.example.bookmanagement.Book.IBookRepository;
import com.example.bookmanagement.loan.ILoanRepository;
import com.example.bookmanagement.loan.LoanFactory;
import com.example.bookmanagement.reservation.IReservationRepository;
import com.example.bookmanagement.reservation.Reservation;
import com.example.bookmanagement.reservation.ReservationFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;
    private final ILoanRepository loanRepository;

    private final IReservationRepository reservationRepository;


    public UserServiceImpl(IUserRepository userRepository, IBookRepository bookRepository, ILoanRepository loanRepository,
                           IReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity<String> loanBook(Long userId, Long bookId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {

            User user = userOptional.get();
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            Book book = bookOptional.get();

            if (bookOptional.isPresent() && book.isAvailable()) {

                book.setStatus("LOANED");
                bookRepository.save(book);

                LocalDate loanDate = LocalDate.parse("2023-05-21");
                LocalDate dueDate = LocalDate.parse("2023-06-21");

                Loan loan = LoanFactory.createLoan(user, book, loanDate, dueDate, user.getName(), book.getTitle());

                user.getLoans().add(loan);
                loanRepository.save(loan);
                userRepository.save(user);

            } else {
                return ResponseEntity.ok("Error: this book is already loaned by someone else.");
            }
        }
        return ResponseEntity.ok("Book loaned successfully");
    }

    @Override
    public void addUser(String name, String email, String phone) {
        userRepository.save(UserFactory.createUser(name, email, phone));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public ResponseEntity<String> reserveBook(Long userId, Long bookId, LocalDate loanStartingDate) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {

            User user = userOptional.get();
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            Book book = bookOptional.get();

            if (bookOptional.isPresent() && book.isAvailable() && !book.isReserved(loanStartingDate)) {

                bookRepository.save(book);

                LocalDate todayDate = LocalDate.parse("2023-06-24");

                Reservation reservation = ReservationFactory.createReservation(user, book, todayDate, loanStartingDate);
                reservationRepository.save(reservation);
            }


        } else {
            return ResponseEntity.ok("Error: this book is already reserved by someone else.");
        }

        return ResponseEntity.ok("Book reserved successfully");
    }
}
