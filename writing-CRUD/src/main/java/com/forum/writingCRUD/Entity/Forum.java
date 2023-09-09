package com.forum.writingCRUD.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@ToString
@Entity(name = "Forum")
@NoArgsConstructor
@Data
public class Forum {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    // 변경사항 업데이트
    public void patch(Forum forum) {
        if(forum.title != null){
            this.title = forum.title;
        }
        if(forum.content != null){
            this.content = forum.content;
        }
    }
}
