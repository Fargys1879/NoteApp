package com.noteapp.service;

import com.noteapp.entity.HashTag;
import com.noteapp.entity.Note;

import java.time.LocalDate;
import java.util.List;

public interface NoteService {
    boolean addNote(Note note);
    boolean updateNote(Note note);
    Note getNoteById(Long id);
    boolean deleteNote(Note note);
    List<Note> getAllNotes();
    List<Note> getNotesByDate(LocalDate date);
    List<Note> getNotesByHashTag(HashTag hashTag);
    List<Note> getNotesContainsSubstring(String substring);
    boolean deleteAll();
}
