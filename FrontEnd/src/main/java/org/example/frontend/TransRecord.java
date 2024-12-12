package org.example.frontend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TransRecord(
        @JsonProperty("transaction_id") int transactionId,
        @JsonProperty("user_id") int userId,
        @JsonProperty("book_id") int bookId,
        String actions,
        String date_of
){}
