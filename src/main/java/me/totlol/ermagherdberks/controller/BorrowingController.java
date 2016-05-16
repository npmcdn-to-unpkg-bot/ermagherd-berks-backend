package me.totlol.ermagherdberks.controller;

import com.google.common.collect.Lists;
import me.totlol.ermagherdberks.controller.jsonobject.BorrowingJsonObject;
import me.totlol.ermagherdberks.entity.Member;
import me.totlol.ermagherdberks.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
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

    @Autowired
    public BorrowingService borrowingService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BorrowingJsonObject> getBorrowings() {
        return Lists.transform(borrowingService.findAllBorrowings(), BorrowingJsonObject::new); // TODO access control
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public BorrowingJsonObject getBorrowing(@PathVariable("id") Long id) {
        return new BorrowingJsonObject(borrowingService.findBorrowingById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public BorrowingJsonObject createBorrowing(@RequestBody BorrowingJsonObject borrowing) {
        System.out.println("Creating borrowing " + borrowing);
        return borrowing;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public BorrowingJsonObject updateBorrowing(@RequestBody BorrowingJsonObject borrowing) {
        return borrowing;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteBorrowing(@PathVariable("id") Long id) {
        borrowingService.deleteBorrowing(id);
    }

}
