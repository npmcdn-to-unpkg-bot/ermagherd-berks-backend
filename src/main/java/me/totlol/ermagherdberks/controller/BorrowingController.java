package me.totlol.ermagherdberks.controller;

import com.google.common.collect.Lists;
import me.totlol.ermagherdberks.controller.jsonobject.BorrowingJsonObject;
import me.totlol.ermagherdberks.entity.Member;
import me.totlol.ermagherdberks.service.BorrowingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by Márton Tóth
 */
@RequestMapping("api/borrowings")
@RestController
public class BorrowingController {

    private static final Logger log = LoggerFactory.getLogger(BorrowingController.class);

    @Autowired
    public BorrowingService borrowingService;

    @RequestMapping(method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public List<BorrowingJsonObject> getBorrowings() {
        return Lists.transform(borrowingService.findAllBorrowings(), BorrowingJsonObject::new); // TODO access control
    }

    @RequestMapping(value = "me", method = RequestMethod.GET)
    @Secured("ROLE_MEMBER")
    public List<BorrowingJsonObject> getMyBorrowings() {
        return Lists.transform(borrowingService.findMyBorrowings(), BorrowingJsonObject::new); // TODO access control
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public BorrowingJsonObject getBorrowing(@PathVariable("id") Long id) {
        return new BorrowingJsonObject(borrowingService.findBorrowingById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public BorrowingJsonObject createBorrowing(@RequestBody BorrowingJsonObject borrowing) {
        log.info("Creating borrowing " + borrowing);
        borrowingService.borrowBook(borrowing.getBookId(), borrowing.getMemberId(), borrowing.getDeadline());
        return borrowing;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @Secured("ROLE_ADMIN")
    public BorrowingJsonObject updateBorrowing(@RequestBody BorrowingJsonObject borrowing) {
        return borrowing;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public void deleteBorrowing(@PathVariable("id") Long id) {
        borrowingService.deleteBorrowing(id);
    }

}
