package me.totlol.ermagherdberks.repository;

import me.totlol.ermagherdberks.entity.Borrowing;
import me.totlol.ermagherdberks.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Márton Tóth
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m where m.email = :email")
    Member findByEmail(@Param("email") String email);

}
