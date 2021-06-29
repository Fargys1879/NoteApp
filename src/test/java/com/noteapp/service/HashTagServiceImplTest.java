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
public class HashTagServiceImplTest {
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

    @Test(expected = ServiceException.class)
    public void getTagByName_WhereNameNotExist() {
        String title = "#noexist";
        HashTag hashTag= null;
        hashTag = hashTagService.getTagByTitle(title);
    }
}