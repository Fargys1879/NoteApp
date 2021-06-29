package com.noteapp.controller.rest;

import com.noteapp.entity.HashTag;
import com.noteapp.entity.Note;
import com.noteapp.service.HashTagService;
import com.noteapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class MainRESTController {
    @Autowired
    NoteService noteService;
    @Autowired
    HashTagService hashTagService;

    @GetMapping(value = "note")
    public Response getFirstNote(@RequestParam() String id) throws ExceptionREST {
        Response response = null;
        Long parseLong = Long.parseLong(id);
        Note noteFromDB = noteService.getNoteById(parseLong);
        response = new Response(HttpStatus.OK,"Note with id = "+ id + " found:", noteFromDB.toString());
        return response;
    }

    @PostMapping("addnote")
    public Response postNote(@RequestBody Note note) throws ExceptionREST {
        Response response = null;

        noteService.addNote(note);
        response = new Response(HttpStatus.OK,"Note Add success",note.toString());
        return response;
    }

    @PostMapping(value = "tag")
    public Response postTag( @RequestBody HashTag tag) throws ExceptionREST {
        Response response = null;

        hashTagService.addTag(tag);
        response = new Response(HttpStatus.OK,"Tag Add success",tag.toString());
        return response;
    }

    @GetMapping("query")
    public Response getNotesByQuery(@RequestParam()   String query,
                                    @RequestParam()   String typeQuery) throws ExceptionREST {
        Response response = null;
        if (typeQuery.equals("0")) {
            List<Note> notes = noteService.getAllNotes();
            String[] noteToJson = new String[notes.size()];
            for (int i = 0; i < notes.size(); i++) {
                noteToJson[i] = notes.get(i).toString();
            }
            response = new Response(HttpStatus.OK,"Notes found:", noteToJson);
        }
        if (typeQuery.equals("1")) {
            LocalDate localDate = LocalDate.parse(query, DateTimeFormatter.ISO_LOCAL_DATE);
            List<Note> notesByDate = noteService.getNotesByDate(localDate);
            String[] noteToJson = new String[notesByDate.size()];
            for (int i = 0; i < notesByDate.size(); i++) {
                noteToJson[i] = notesByDate.get(i).toString();
            }
            response = new Response(HttpStatus.OK,"Notes with date" + query + ":",noteToJson);
        }
        if (typeQuery.equals("2")) {
            HashTag hashTag = hashTagService.getTagByTitle(query);
            List<Note> notesByHashTag = noteService.getNotesByHashTag(hashTag);
            String[] noteToJson = new String[notesByHashTag.size()];
            for (int i = 0; i < notesByHashTag.size(); i++) {
                noteToJson[i] = notesByHashTag.get(i).toString();
            }
            response = new Response(HttpStatus.OK,"Notes with HashTag" + hashTag + ":",noteToJson);
        }
        if (typeQuery.equals("3")) {
            List<Note> notesBySubstring = noteService.getNotesContainsSubstring(query);
            String[] noteToJson = new String[notesBySubstring.size()];
            for (int i = 0; i < notesBySubstring.size(); i++) {
                noteToJson[i] = notesBySubstring.get(i).toString();
            }
            response = new Response(HttpStatus.OK,"Notes with Substring" + query + ":",noteToJson);
        }
        return response;
    }
}
