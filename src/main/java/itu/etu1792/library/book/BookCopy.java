package itu.etu1792.library.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_copy")
@Getter
@Setter
public class BookCopy {
    public enum Status {
        AVAILABLE, RESERVED, UNAVAILABLE, SPECIAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "copy_status", nullable = false)
    private Status status = Status.AVAILABLE;

    @Column(name = "shelf_location", length = 100)
    private String shelfLocation;

    @Column(length = 50)
    private String room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
