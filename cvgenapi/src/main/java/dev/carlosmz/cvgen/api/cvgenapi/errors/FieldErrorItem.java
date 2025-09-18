package dev.carlosmz.cvgen.api.cvgenapi.errors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldErrorItem {

    private String field;
    private Object rejectedValue;
    private String message;
    
}
