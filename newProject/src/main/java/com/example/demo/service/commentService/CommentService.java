package com.example.demo.service.commentService;

import com.example.demo.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();

    Comment findById(int idComment);

    void save(Comment comment);

    void delete(int idComment);
}
