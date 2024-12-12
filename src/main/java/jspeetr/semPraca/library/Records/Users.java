package jspeetr.semPraca.library.Records;

public record Users(
    int user_id,
    String username,
    String password,
    int role_id,
    String email
) {}
