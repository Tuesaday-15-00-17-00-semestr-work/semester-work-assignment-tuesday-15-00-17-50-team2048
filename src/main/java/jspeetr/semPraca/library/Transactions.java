package jspeetr.semPraca.library;

public record Transactions(
        int transaction_id,
        int user_id,
        int book_id,
        String actions,
        String date_of
) {}
