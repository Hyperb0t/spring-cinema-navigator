package ru.itis.springcinemanavigator.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_generator")
    @SequenceGenerator(name="tag_generator", sequenceName = "tag_seq", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "tags")
    private Set<Film> films;

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
