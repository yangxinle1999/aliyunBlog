package com.yang.service;

import com.yang.po.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> listCommentByBlogId(Long id);
    Comment saveComment(Comment comment);
}
