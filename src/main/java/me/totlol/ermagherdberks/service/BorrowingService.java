package me.totlol.ermagherdberks.service;

import me.totlol.ermagherdberks.entity.Borrowing;
import me.totlol.ermagherdberks.repository.BookRepository;
import me.totlol.ermagherdberks.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Márton Tóth
 */
@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    public List<Borrowing> findAllBorrowingsForMember(Long memberId) {
        return borrowingRepository.findAllForMember(memberId);
    }

    public List<Borrowing> findAllBorrowings() {
        return borrowingRepository.findAll();
    }

    public Borrowing borrowBook(Long bookId, Long memberId) {
        return null; // TODO
    }

    public void deleteBorrowing(Long borrowingId) {
        borrowingRepository.delete(borrowingId);
    }

    public Borrowing findBorrowingById(Long borrowingId) {
        return null; // TODO
    }

}
