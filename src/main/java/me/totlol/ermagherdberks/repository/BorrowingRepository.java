package me.totlol.ermagherdberks.repository;

import me.totlol.ermagherdberks.entity.Book;
import me.totlol.ermagherdberks.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Márton Tóth
 */
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    @Query("SELECT b FROM Borrowing b WHERE b.member.id = :memberId")
    List<Borrowing> findAllForMember(@Param("memberId") Long memberId);

}
