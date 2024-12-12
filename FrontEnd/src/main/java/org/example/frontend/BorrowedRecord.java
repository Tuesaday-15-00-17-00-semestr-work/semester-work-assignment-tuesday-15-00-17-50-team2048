package org.example.frontend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BorrowedRecord(
        @JsonProperty("borrow_id") int borrowedId,
        String title,
        String author,
        String date_of,
        @JsonProperty("transaction_id") int transactionId
) {}