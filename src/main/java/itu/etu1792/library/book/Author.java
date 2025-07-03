package itu.etu1792.library.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String biography;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public String getFullName() {
        return firstName + " " + lastName;
    }
}