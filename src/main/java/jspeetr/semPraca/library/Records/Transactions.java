package jspeetr.semPraca.library.Records;

public record Transactions(
        int transaction_id,
        int user_id,
        int book_id,
        String actions,
        String date_of
) {}
