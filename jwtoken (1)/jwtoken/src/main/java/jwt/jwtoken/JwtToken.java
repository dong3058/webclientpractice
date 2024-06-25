package jwt.jwtoken;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtToken {


    private String accesstoken;
    private String refreshtoken;
    private String grantType;

}
