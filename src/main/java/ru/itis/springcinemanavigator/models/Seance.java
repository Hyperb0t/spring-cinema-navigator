package ru.itis.springcinemanavigator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seance_generator")
    @SequenceGenerator(name="seance_generator", sequenceName = "seance_seq", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private ru.itis.springcinemanavigator.models.Film film;
    private Instant startTime;
    private Instant endTime;
    private Integer price;
    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orders;

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", film=" + film +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                '}';
    }
}
