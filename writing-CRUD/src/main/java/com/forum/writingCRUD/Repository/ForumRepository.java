package com.forum.writingCRUD.Repository;

import com.forum.writingCRUD.Entity.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Long> {

}
