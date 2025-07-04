package itu.etu1792.library.members;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member {
    public enum MemberType {
        STUDENT("Étudiant"),
        PROFESSIONAL("Professionnel"),
        TEACHER("Professeur");

        private final String displayName;

        MemberType(String displayName) {
            this.displayName = displayName;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType type;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "membership_expiry", nullable = false)
    private LocalDate membershipExpiry;

    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    public String getFullName() {
        return firstName + " " + lastName;
    }
}