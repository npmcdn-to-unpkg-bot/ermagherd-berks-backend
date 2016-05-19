package me.totlol.ermagherdberks.service;

import me.totlol.ermagherdberks.entity.Book;
import me.totlol.ermagherdberks.entity.Borrowing;
import me.totlol.ermagherdberks.entity.Member;
import me.totlol.ermagherdberks.repository.BookRepository;
import me.totlol.ermagherdberks.repository.BorrowingRepository;
import me.totlol.ermagherdberks.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Márton Tóth
 */
@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Borrowing> findAllBorrowingsForMember(Long memberId) {
        return borrowingRepository.findAllForMember(memberId);
    }

    public List<Borrowing> findAllBorrowings() {
        return borrowingRepository.findAll();
    }

    public Borrowing borrowBook(Long bookId, Long memberId, Long deadline) {
        Member member = memberRepository.findOne(memberId);
        Book book = bookRepository.findOne(bookId);
        Borrowing borrowing = new Borrowing();
        borrowing.setMember(member);
        borrowing.setBook(book);
        borrowing.setTimeOfBorrowing(System.currentTimeMillis());
        borrowing.setDeadline(deadline);
        return borrowingRepository.save(borrowing);
    }

    public void deleteBorrowing(Long borrowingId) {
        borrowingRepository.delete(borrowingId);
    }

    public Borrowing findBorrowingById(Long borrowingId) {
        return null; // TODO
    }

    public List<Borrowing> findMyBorrowings() {
        Member me = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return borrowingRepository.findAllForMember(me.getId());
    }
}
