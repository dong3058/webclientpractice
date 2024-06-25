package sprginsecuritytest.security;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Member {

    @Id
    private String username;


    private String password;

    private String role;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Member() {
    }
}
