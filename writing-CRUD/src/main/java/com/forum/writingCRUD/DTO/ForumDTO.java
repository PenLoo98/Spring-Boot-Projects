package com.forum.writingCRUD.DTO;

import com.forum.writingCRUD.Entity.Comment;
import com.forum.writingCRUD.Entity.Forum;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ToString
public class ForumDTO {
    private Long id;
    private String title;
    private String content;
    private List<Comment> commentList = new ArrayList<>();
    
    public Forum toEntity() {
        return new Forum(id, title, content, commentList);
    }
}
