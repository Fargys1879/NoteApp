package com.noteapp.service;

import com.noteapp.entity.HashTag;

import java.util.List;

public interface HashTagService {
    boolean addTag(HashTag hashTag);
    boolean updateTag(HashTag tag);
    HashTag getTagById(Long id);
    HashTag getTagByTitle(String title);
    boolean deleteTag(HashTag tag);
    List<HashTag> getAllTags();
    boolean deleteAll();
}
