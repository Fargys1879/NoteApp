package com.noteapp.controller;

import com.noteapp.entity.HashTag;
import com.noteapp.entity.Note;
import com.noteapp.service.HashTagService;
import com.noteapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Controller
@RequestMapping(path = "/")
public class MainController {
    @Autowired
    NoteService noteService;
    @Autowired
    HashTagService hashTagService;

    private Note note = new Note("title","text", LocalDate.of(2021,3,3));
    private Note note1 = new Note("title1","text1", LocalDate.of(2021,1,1));
    private Note note2 = new Note("title2","text2", LocalDate.of(2021,2,2));
    private HashTag hashTag = new HashTag("#beauty");
    private HashTag hashTag1 = new HashTag("#sport");
    private HashTag hashTag2 = new HashTag("#smart");

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String getIndex() {
        noteService.addNote(note);
        noteService.addNote(note1);
        noteService.addNote(note2);
        hashTagService.addTag(hashTag);
        hashTagService.addTag(hashTag1);
        hashTagService.addTag(hashTag2);

        hashTag.setNotes(noteService.getAllNotes());
        hashTagService.updateTag(hashTag);
        return "index";
    }
}
