package com.noteapp.controller.rest;

import com.noteapp.config.AppConfig;
import com.noteapp.entity.HashTag;
import com.noteapp.entity.Note;
import com.noteapp.service.HashTagService;
import com.noteapp.service.NoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@ContextConfiguration(  classes = AppConfig.class,
                        loader = AnnotationConfigContextLoader.class)
public class MainRESTControllerTest {
    @Autowired
    MainRESTController mainRESTController;
    @Autowired
    HashTagService hashTagService;
    @Autowired
    NoteService noteService;

    private Note note = new Note("title","text", LocalDate.of(2021,3,3));
    private Note note1 = new Note("title1","text1", LocalDate.of(2021,1,1));
    private Note note2 = new Note("title2","text2", LocalDate.of(2021,2,2));
    private Note note3 = new Note("title4","text4", LocalDate.of(2021,4,4));
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
    public void getNoteTest_WhereId() {
        try {
            Object object = mainRESTController.getFirstNote("1");
            System.out.println(object);
        } catch (ExceptionREST exceptionREST) {
            exceptionREST.printStackTrace();
        }
    }

    @Test
    public void postNoteTest() {
        try {
            Object object = mainRESTController.postNote(note3);
            System.out.println(object);
        } catch (ExceptionREST exceptionREST) {
            exceptionREST.printStackTrace();
        }
    }

    @Test
    public void postTagTest() {
        try {
            Object object = mainRESTController.postTag(new HashTag("#rest"));
            System.out.println(object);
        } catch (ExceptionREST exceptionREST) {
            exceptionREST.printStackTrace();
        }
    }

    @Test
    public void getNotesByQueryTest_WhereDate() {
        try {
            Object object = mainRESTController.getNotesByQuery("2021-04-04","1");
            System.out.println(object);
        } catch (ExceptionREST exceptionREST) {
            exceptionREST.printStackTrace();
        }
    }

    @Test
    public void getNotesByQueryTest_WhereHashTag() {
        Object object1 = null;
        try {
            object1 = mainRESTController.getNotesByQuery("#beauty","2");
        } catch (ExceptionREST exceptionREST) {
            exceptionREST.printStackTrace();
        }
        System.out.println(object1);
    }

    @Test
    public void getNotesByQueryTest_WhereSubstring() {
        Object object2 = null;
        try {
            object2 = mainRESTController.getNotesByQuery("ext","3");
        } catch (ExceptionREST exceptionREST) {
            exceptionREST.printStackTrace();
        }
        System.out.println(object2);
    }
}