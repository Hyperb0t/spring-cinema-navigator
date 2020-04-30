package ru.itis.springcinemanavigator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_generator")
    @SequenceGenerator(name="film_generator", sequenceName = "film_seq", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String title;
    private String description;
    private String imageLink;
    private Duration duration;
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Seance> seances;
    @ManyToMany
    @JoinTable(
            name = "film_tag",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", duration=" + duration +
                ", tags=" + tags +
                '}';
    }
}
