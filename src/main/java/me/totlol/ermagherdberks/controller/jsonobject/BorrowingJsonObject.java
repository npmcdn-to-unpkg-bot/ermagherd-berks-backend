package me.totlol.ermagherdberks.controller.jsonobject;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Márton Tóth
 */
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

}
