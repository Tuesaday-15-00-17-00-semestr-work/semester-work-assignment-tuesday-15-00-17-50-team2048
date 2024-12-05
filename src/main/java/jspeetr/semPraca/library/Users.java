package jspeetr.semPraca.library;

public record Users(
    int user_id,
    String username,
    String password,
    int role_id,
    String email
) {}
