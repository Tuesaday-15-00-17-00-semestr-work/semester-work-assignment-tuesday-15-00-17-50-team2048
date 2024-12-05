package jspeetr.semPraca.library;

import java.time.LocalDateTime;

public record Transactions(
        int transaction_id,
        int user_id,
        int book_id,
        int actions,
        LocalDateTime date_of
) {}
