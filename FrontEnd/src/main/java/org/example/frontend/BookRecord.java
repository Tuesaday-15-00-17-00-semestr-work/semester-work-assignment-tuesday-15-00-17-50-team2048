package org.example.frontend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookRecord(
        @JsonProperty("book_id") int bookId,
        String title,
        String author,
        String isbn,
        @JsonProperty("available_copies") int availableCopies
) {}
