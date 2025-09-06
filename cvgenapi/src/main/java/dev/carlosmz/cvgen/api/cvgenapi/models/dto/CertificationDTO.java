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

    @NotBlank @Size(max = 200)
    private String name;

    @NotBlank @Size(max = 200)
    private String issuer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dateObtained;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate validUntil;

    @Size(max = 120)
    private String credentialId;

    @URL @Size(max = 512)
    private String credentialUrl;

}
