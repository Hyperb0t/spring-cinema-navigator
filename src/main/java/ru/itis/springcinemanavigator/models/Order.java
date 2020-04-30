package ru.itis.springcinemanavigator.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "public", name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seance_generator")
    @SequenceGenerator(name="seance_generator", sequenceName = "seance_seq", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private ru.itis.springcinemanavigator.models.Seance seance;
    private Instant datetime;
    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private ru.itis.springcinemanavigator.models.User user;
    private Integer place;
}
