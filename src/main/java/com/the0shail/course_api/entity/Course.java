package com.the0shail.course_api.entity;

import com.the0shail.course_api.entity.enumerate.PublicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PublicationStatus status = PublicationStatus.DRAFT;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp

    private Instant createdAt = Instant.now();

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt = Instant.now();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_categories",                          // имя связующей таблицы
            joinColumns = @JoinColumn(name = "course_id"),       // колонка → сюда, к Course
            inverseJoinColumns = @JoinColumn(name = "category_id") // колонка → туда, к Category
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Module> modules = new ArrayList<>();

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
