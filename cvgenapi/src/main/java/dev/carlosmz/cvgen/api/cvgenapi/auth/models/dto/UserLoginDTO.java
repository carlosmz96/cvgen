package dev.carlosmz.cvgen.api.cvgenapi.auth.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO {

    @NotBlank(message = "{user.login.username.notBlank}")
    private String username;

    @NotBlank(message = "{user.login.password.notBlank}")
    private String password;
    
}