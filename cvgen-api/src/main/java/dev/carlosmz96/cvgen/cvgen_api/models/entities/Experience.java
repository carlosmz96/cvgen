package dev.carlosmz96.cvgen.cvgen_api.models.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;
    private String company;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean current;
    private String description;

    @ManyToOne
    private Curriculum curriculum;
    
}
