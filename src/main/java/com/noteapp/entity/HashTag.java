package com.noteapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "notes")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hashtags")
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "hash_tag_title")
    @NonNull
    private String hashTagTitle = "#hashtag";

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
    @JoinTable(
            name = "HashTags_Notes",
            joinColumns = { @JoinColumn(name = "tag_id") },
            inverseJoinColumns = { @JoinColumn(name = "note_id") }
            )
    private List<Note> notes;

    public HashTag(String hashTagTitle) {
        this.hashTagTitle = hashTagTitle;
    }
}
