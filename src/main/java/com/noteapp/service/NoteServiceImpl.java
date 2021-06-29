package com.noteapp.service;

import com.noteapp.dao.NoteDAO;
import com.noteapp.entity.HashTag;
import com.noteapp.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteDAO noteDAO;

    @Override
    @Transactional
    public boolean addNote(Note note) {
        try {
            noteDAO.save(note);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public boolean updateNote(Note note) {
        try {
            noteDAO.update(note);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Note getNoteById(Long id) {
        Note result = null;
        try {
            result = noteDAO.findOne(id);
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public boolean deleteNote(Note note) {
        try {
            noteDAO.delete(note);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public List<Note> getAllNotes() {
        List<Note> result = null;
        try {
            result = noteDAO.findAll();
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public List<Note> getNotesByDate(LocalDate date) {
        List<Note> result = null;
        try {
            result = noteDAO.findByLocalDate(date);
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public List<Note> getNotesByHashTag(HashTag hashTag) {
        List<Note> result = null;
        try {
            Long tagId = hashTag.getId();
            result = noteDAO.getNotesByTagId(tagId);
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public List<Note> getNotesContainsSubstring(String substring) {
        List<Note> result = new ArrayList<>();
        List<Note> allNotes = null;
        try {
            allNotes = noteDAO.findAll();
            for (Note note : allNotes) {
                String string = note.getNoteText();
                if (string.contains(substring)) {
                    result.add(note);
                }
            }
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public boolean deleteAll() {
        try {
            noteDAO.deleteAll();
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
