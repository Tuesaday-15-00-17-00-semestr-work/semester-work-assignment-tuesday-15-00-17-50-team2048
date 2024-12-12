package jspeetr.semPraca.library.Records;

public record Book(
        int book_id,
        String title,
        String author,
        String isbn,
        int available_copies
) {}
