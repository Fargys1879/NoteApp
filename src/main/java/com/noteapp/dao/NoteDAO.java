package com.noteapp.dao;

import com.noteapp.entity.Note;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NoteDAO implements DAO<Long, Note>{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Note entity) {
        getSession().persist( entity );
    }

    @Override
    public Note findOne(Long key) {
        return getSession().get(Note.class,key);
    }

    @Override
    public Note update(Note entity) {
        return (Note) getSession().merge( entity );
    }

    @Override
    public void delete(Note entity) {
        getSession().delete( entity );
    }

    @Override
    public List<Note> findAll() {
        return getSession()
                .createQuery( "from " + Note.class.getName() ).list();
    }

    public List<Note> getNotesByTagId(Long tagId) {
        List<Long> noteIdList = null;
        Query query = getSession().createSQLQuery("SELECT note_id FROM HashTags_Notes\n" +
                "WHERE HashTags_Notes.tag_id = " + tagId )
                .addScalar("note_id", new LongType()
                );
        noteIdList = (List<Long>) query.getResultList();
        List<Note> result = new ArrayList<>();
        for (Long id : noteIdList) {
            Note note = getSession().get(Note.class,id);
            result.add(note);
        }
        return result;
    }

    public void deleteAll() {
        Query query = getSession().createSQLQuery("DELETE From HashTags_Notes;\n" +
                "DELETE From Notes;");
        query.executeUpdate();
    }

    public List<Note> findByLocalDate(LocalDate localDate) {
        List<Note> result = null;
        Query query = getSession().createQuery("from Note where addTime = :paramName");
        query.setParameter("paramName", localDate);
        result = query.list();
        return result;
    }
}
