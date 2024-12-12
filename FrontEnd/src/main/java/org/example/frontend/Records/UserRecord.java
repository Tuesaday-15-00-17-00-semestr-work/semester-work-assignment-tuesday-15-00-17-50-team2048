package org.example.frontend.Records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRecord(
        @JsonProperty("user_id") int userId,
        String username,
        String password,
        @JsonProperty("role_id") int roleId,
        String email
) {}
