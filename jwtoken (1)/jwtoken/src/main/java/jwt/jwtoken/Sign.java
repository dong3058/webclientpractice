package jwt.jwtoken;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Sign {


    public Sign() {
    }

    @Id
    private String username;
    private String password;
    private String role;


}
