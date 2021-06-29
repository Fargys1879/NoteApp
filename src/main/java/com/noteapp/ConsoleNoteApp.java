package com.noteapp;

import com.noteapp.config.AppConfig;
import com.noteapp.entity.HashTag;
import com.noteapp.entity.Note;
import com.noteapp.service.HashTagService;
import com.noteapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConsoleNoteApp {
    static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    @Autowired
    static NoteService noteService;
    @Autowired
    static HashTagService hashTagService;
    static String noteTitle = "defaultName";
    static String noteText = "defaultText";
    static String hashTagTitle = "#defaultHashTag";
    static String dateForSearch = "2021-01-01";
    static String subString = "ext1";

    public static void main(String[] args)  {
        noteService = applicationContext.getBean(NoteService.class);
        hashTagService = applicationContext.getBean(HashTagService.class);
        initDB();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            action(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initDB() {
        Note note = new Note("title","text", LocalDate.of(2021,3,3));
        Note note1 = new Note("title1","text1", LocalDate.of(2021,1,1));
        Note note2 = new Note("title2","text2", LocalDate.of(2021,2,2));
        HashTag hashTag = new HashTag("#beauty");
        HashTag hashTag1 = new HashTag("#sport");
        HashTag hashTag2 = new HashTag("#smart");

        noteService.addNote(note);
        noteService.addNote(note1);
        noteService.addNote(note2);
        hashTagService.addTag(hashTag);
        hashTagService.addTag(hashTag1);
        hashTagService.addTag(hashTag2);

        HashTag hashTagFromDb = hashTagService.getTagById(1L);
        List<Note> notes = noteService.getAllNotes();
        hashTagFromDb.setNotes(notes);
        hashTagService.updateTag(hashTagFromDb);
    }

    public static void action(BufferedReader bufferedReader) throws IOException {
        System.out.println("Выберите действие:\n" +
                "'0' - добавить новую Заметку\n" +
                "'1' - найти все заметки с подходящей датой\n" +
                "'2' - найти все заметки с ХэшТэгом\n" +
                "'3' - найти все заметки\n" +
                "'4' - найти заметки содержащие подстроку");
        String action = "3";
        try {
            action = bufferedReader.readLine();
            if (action.equals("0")) {
                System.out.println("Добавьте нaзвание статьи");
                noteTitle = bufferedReader.readLine();
                System.out.println("Добавьте текст статьи");
                noteText = bufferedReader.readLine();
                System.out.println("Добавьте хэштэг");
                hashTagTitle = bufferedReader.readLine();
            }
            if (action.equals("1")) {
                System.out.println("Введите дату в формате: YYYY-MM-DD");
                dateForSearch = bufferedReader.readLine();
            }
            if (action.equals("2")) {
                System.out.println("Введите ХэшТэг: #hashtag");
                hashTagTitle = bufferedReader.readLine();
            }
            if (action.equals("4")) {
                System.out.println("Введите Подстроку запроса: ");
                subString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert bufferedReader != null;
            bufferedReader.close();
        }
        switch (action) {
            case  ("0"):
                addNewNote();
                break;
            case ("1"):
                getNotesByDate(dateForSearch);
                break;
            case ("2"):
                getNotesByHashTag(hashTagTitle);
                break;
            case ("4"):
                getNotesContainsSubstring(subString);
                break;
            default:
                getAllNotes();
                break;
        }
    }

    public static void addNewNote() {
        Note note = new Note(noteTitle,noteText);
        noteService.addNote(note);
        HashTag hashTag = new HashTag(hashTagTitle);
        hashTagService.addTag(hashTag);
        List<Note> notes = new ArrayList<Note>();
        notes.add(note);
        hashTag.setNotes(notes);
        hashTagService.updateTag(hashTag);
        System.out.println("Навая Заметка успешно добавлена");
    }

    public static void getNotesByDate(String dateForSearch) {
        LocalDate localDate = LocalDate.parse(dateForSearch, DateTimeFormatter.ISO_LOCAL_DATE);
        List<Note> notes = noteService.getNotesByDate(localDate);
        for (Note note : notes) {
            System.out.println(note.getNoteTitle() + " " + note.getNoteText() + " " + note.getAddTime() );
        }
    }

    public static void getNotesByHashTag(String hashTagTitle) {
        HashTag hashTag = hashTagService.getTagByTitle(hashTagTitle);
        List<Note> notes = noteService.getNotesByHashTag(hashTag);
        for (Note note : notes) {
            System.out.println(note.getNoteTitle() + " " + note.getNoteText() + " " + note.getAddTime() );
        }
    }

    public static void getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        for (Note note : notes) {
            System.out.println(note.getNoteTitle() + " " + note.getNoteText() + " " + note.getAddTime() );
        }
    }

    public static void getNotesContainsSubstring(String subString) {
        List<Note> notes = noteService.getNotesContainsSubstring(subString);
        for (Note note : notes) {
            System.out.println(note.getNoteTitle() + " " + note.getNoteText() + " " + note.getAddTime() );
        }
    }
}
