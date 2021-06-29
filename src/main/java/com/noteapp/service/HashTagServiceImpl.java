package com.noteapp.service;

import com.noteapp.dao.HashTagDAO;
import com.noteapp.entity.HashTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HashTagServiceImpl implements HashTagService {
    @Autowired
    HashTagDAO hashTagDAO;

    @Override
    @Transactional
    public boolean addTag(HashTag hashTag) {
        try {
            hashTagDAO.save(hashTag);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public boolean updateTag(HashTag tag) {
        try {
            hashTagDAO.update(tag);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public HashTag getTagById(Long id) {
        HashTag result = null;
        try {
            result = hashTagDAO.findOne(id);
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public HashTag getTagByTitle(String title) {
        HashTag result = null;
        try {
            result = hashTagDAO.findByName(title);
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public boolean deleteTag(HashTag tag) {
        try {
            hashTagDAO.delete(tag);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public List<HashTag> getAllTags() {
        List<HashTag> result = null;
        try {
            result = hashTagDAO.findAll();
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public boolean deleteAll() {
        try {
            hashTagDAO.deleteAll();
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
