package com.noteapp.service;

import com.noteapp.config.AppConfig;
import com.noteapp.entity.HashTag;
import com.noteapp.entity.Note;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class NoteServiceImplTest {
    @Autowired
    private NoteService noteService;
    @Autowired
    private HashTagService hashTagService;

    private Note note = new Note("title","text", LocalDate.of(2021,3,3));
    private Note note1 = new Note("title1","text1", LocalDate.of(2021,1,1));
    private Note note2 = new Note("title2","text2", LocalDate.of(2021,2,2));
    private HashTag hashTag = new HashTag("#beauty");
    private HashTag hashTag1 = new HashTag("#sport");
    private HashTag hashTag2 = new HashTag("#smart");

    @Before
    public void setUp() throws Exception {
       noteService.addNote(note);
       noteService.addNote(note1);
       noteService.addNote(note2);
       hashTagService.addTag(hashTag);
       hashTagService.addTag(hashTag1);
       hashTagService.addTag(hashTag2);

       hashTag.setNotes(noteService.getAllNotes());
       hashTagService.updateTag(hashTag);
    }

    @Test
    public void addNote() {
        Note note = new Note("title3","text3");
        boolean check = noteService.addNote(note);
        Assert.assertTrue(check);
    }

    @Test
    public void updateNote() {
        Note noteFromDb = noteService.getNoteById(1L);
        noteFromDb.setNoteText("Updated text");
        boolean check = noteService.updateNote(noteFromDb);
        Assert.assertTrue(check);
    }

    @Test
    public void getNoteByUid() {
        Long id = 1L;
        Note noteFromDb = noteService.getNoteById(1L);
        Assert.assertEquals(id,noteFromDb.getId());
    }

    @Test
    public void getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        for (Note note : notes) {
            System.out.println(note.getNoteTitle() + " " + note.getNoteText() + " " + note.getAddTime() );
        }
    }

    @Test
    public void getNotesByDate() {
        LocalDate date = LocalDate.of(2021,3,3);
        List<Note> notes = noteService.getNotesByDate(date);
        for (Note note : notes) {
            System.out.println(note.getNoteTitle() + " " + note.getNoteText() + " " + note.getAddTime() );
        }
    }

    @Test
    public void getNotesContainsSubstring() {
        String substring = "ext1";
        List<Note> notes = noteService.getNotesContainsSubstring(substring);
        for (Note note : notes) {
            System.out.println(note.getNoteTitle() + " " + note.getNoteText() + " " + note.getAddTime() );
        }
    }
}