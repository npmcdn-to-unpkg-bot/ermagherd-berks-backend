package me.totlol.ermagherdberks.repository;

import me.totlol.ermagherdberks.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Márton Tóth
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
}
