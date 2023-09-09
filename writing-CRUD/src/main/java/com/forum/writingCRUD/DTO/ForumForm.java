package com.forum.writingCRUD.DTO;

import com.forum.writingCRUD.Entity.Forum;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ForumForm {
    private Long id;
    private String title;
    private String content;
    
    public Forum toEntity() {
        return new Forum(id, title, content);
    }
}
