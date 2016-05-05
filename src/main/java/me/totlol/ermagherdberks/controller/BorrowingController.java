package me.totlol.ermagherdberks.controller;

import me.totlol.ermagherdberks.controller.jsonobject.BorrowingJsonObject;
import me.totlol.ermagherdberks.entity.Member;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by Márton Tóth
 */
@RequestMapping("api/borrowings")
@RestController
public class BorrowingController {


    @RequestMapping(method = RequestMethod.GET)
    public List<BorrowingJsonObject> getBorrowings() {
        return Collections.emptyList();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public BorrowingJsonObject getBorrowing(@PathVariable("id") Long id) {
        return new BorrowingJsonObject();
    }

    @RequestMapping(method = RequestMethod.POST)
    public BorrowingJsonObject createBorrowing(@RequestBody BorrowingJsonObject borrowing) {
        return borrowing;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public BorrowingJsonObject updateBorrowing(@RequestBody BorrowingJsonObject borrowing) {
        return borrowing;
    }

}
