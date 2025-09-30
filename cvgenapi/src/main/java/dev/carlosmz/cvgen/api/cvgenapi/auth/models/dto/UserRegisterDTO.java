package dev.carlosmz.cvgen.api.cvgenapi.auth.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class UserRegisterDTO {

    @NotBlank(message = "{user.register.fullName.notBlank}")
    @Size(max = 200, message = "{user.register.fullName.size}")
    private String fullName;

    @NotBlank(message = "{user.register.email.notBlank}")
    @Email(message = "{user.register.email.email}")
    @Size(max = 320, message = "{user.register.email.size}")
    private String email;

    @NotBlank(message = "{user.register.username.notBlank}")
    @Size(max = 50, message = "{user.register.username.size}")
    private String username;

    @NotBlank(message = "{user.register.password.notBlank}")
    @Size(min = 6, message = "{user.register.password.size}")
    private String password;

    private boolean enabled;

}