package com.yang.service;

import com.yang.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    Tag savaTag(Tag tag);
    Tag getTag(Long id);
    Page<Tag> listTag(Pageable pageable);
    Tag updateTag(Long id,Tag tag);
    List<Tag> listTagTop(Integer size);
    void deleteTag(Long id);
    Tag getTagByName(String name);
    List<Tag> listTag();
    List<Tag> listTag(String ids);
}
