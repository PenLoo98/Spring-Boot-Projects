INSERT INTO article(id,title, content) VALUES (1,'제목 1', '1111');
INSERT INTO article(id,title, content) VALUES (2,'제목 2', '2222');
INSERT INTO article(id,title, content) VALUES (3,'제목 3', '3333');

INSERT INTO member (id, email, password) VALUES (1, 'hong@test.com', 'password');
INSERT INTO member (id, email, password) VALUES (2, 'kim@test.com', 'password');
INSERT INTO member (id, email, password) VALUES (3, 'park@test.com', 'password');

-- entity comment
create table comment (
                         id bigint not null auto_increment,
                         body varchar(255),
                         nickname varchar(255),
                         article_id bigint,
                         primary key (id)
)