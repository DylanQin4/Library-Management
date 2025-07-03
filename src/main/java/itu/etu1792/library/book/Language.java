package itu.etu1792.library.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "language")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String code;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "language")
    private Set<Book> books = new HashSet<>();

    // Méthode pratique pour l'affichage
    public String getDisplayName() {
        return name + " (" + code + ")";
    }
}
