package com.noteapp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"id", "addTime"})
@ToString(exclude = "hashTags")
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "note_title")
    private String noteTitle = "NoTitle";
    @Column(name = "note_text")
    private String noteText = "NoText";
    @Column(name = "add_time")
    private LocalDate addTime = LocalDate.now();

    @ManyToMany(mappedBy = "notes",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<HashTag> hashTags;

    public Note(String noteTitle, String noteText) {
        this.noteTitle = noteTitle;
        this.noteText = noteText;
    }

    public Note(String noteTitle, String noteText, LocalDate addTime) {
        this.noteTitle = noteTitle;
        this.noteText = noteText;
        this.addTime = addTime;
    }
}
