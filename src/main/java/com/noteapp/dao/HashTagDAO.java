package com.noteapp.dao;

import com.noteapp.entity.HashTag;
import com.noteapp.entity.Note;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HashTagDAO implements DAO<Long, HashTag> {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(HashTag entity) {
        getSession().persist( entity );
    }

    @Override
    public HashTag findOne(Long key) {
        return getSession().get(HashTag.class,key);
    }

    public HashTag findByName(String title) {
        HashTag result = null;
        Query query = getSession().createQuery("from HashTag where hashTagTitle = :paramName");
        query.setParameter("paramName", title);
        result = (HashTag) query.getSingleResult();
        return result;
    }

    @Override
    public HashTag update(HashTag entity) {
        return (HashTag) getSession().merge( entity );
    }

    @Override
    public void delete(HashTag entity) {
        getSession().delete( entity );
    }

    @Override
    public List<HashTag> findAll() {
        return getSession()
                .createQuery( "from " + HashTag.class.getName() ).list();
    }

    public void deleteAll() {
        Query query = getSession().createSQLQuery("DELETE From HashTags_Notes;\n" +
                "DELETE From HashTags;");
        query.executeUpdate();
    }
}
