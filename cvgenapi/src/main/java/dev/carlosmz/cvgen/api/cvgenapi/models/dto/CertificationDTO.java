package dev.carlosmz.cvgen.api.cvgenapi.models.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificationDTO {

    private Long id;

    @NotBlank(message = "{cert.name.notBlank}")
    @Size(max = 200, message = "{cert.name.size}")
    private String name;

    @NotBlank(message = "{cert.issuer.notBlank}")
    @Size(max = 200, message = "{cert.issuer.size}")
    private String issuer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "{cert.dateObtained.notNull}")
    private LocalDate dateObtained;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate validUntil;

    @Size(max = 120, message = "{cert.credentialId.size}")
    private String credentialId;

    @URL(message = "{cert.credencialUrl.url}")
    @Size(max = 512, message = "{cert.credencialUrl.size}")
    private String credentialUrl;

}
