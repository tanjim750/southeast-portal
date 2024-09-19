package seu.edu.bd.southeast_portal.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    USER_READ("user:read"),

    STUFF_READ("stuff:read"),
    STUFF_UPDATE("stuff:update"),

    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    ;

    @Getter
    private final String permission;
}
