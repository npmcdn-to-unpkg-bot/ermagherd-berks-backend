package me.totlol.ermagherdberks.controller.jsonobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.totlol.ermagherdberks.entity.Borrowing;

/**
 * Created by Márton Tóth
 */
@Data
public class BorrowingJsonObject {

    @JsonProperty
    private Long id;
    @JsonProperty
    private Long bookId;
    @JsonProperty
    private Long memberId;
    @JsonProperty
    private Long timeOfBorrowing;
    @JsonProperty
    private Long deadline;

    public BorrowingJsonObject(Borrowing borrowing) {
        id = borrowing.getId();
        bookId = borrowing.getBook().getId();
        memberId = borrowing.getMember().getId();
        timeOfBorrowing = borrowing.getTimeOfBorrowing();
        deadline = borrowing.getDeadline();
    }

    public BorrowingJsonObject() {
    }
}
