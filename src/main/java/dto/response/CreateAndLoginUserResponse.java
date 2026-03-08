package dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAndLoginUserResponse {
    private boolean success;
    private User user;
    private String accessToken;
    private String refreshToken;
}
