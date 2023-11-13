package de.tekup.tunirent.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserSearchRequest extends PersonRequest {
    private String username;
}
