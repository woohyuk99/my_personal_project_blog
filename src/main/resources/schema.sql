CREATE TABLE users(

    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    nickname VARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    has_avatar BOOL NOT NULL DEFAULT false,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(), /* db에서는 snake_case를 주로 샤용*/
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now()

);

CREATE TABLE posts(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(100) NOT NULL,
    tags VARCHAR(100) NOT NULL,
    category VARCHAR(100) NOT NULL,
    author_id BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    CONSTRAINT fk_blog_author FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE

);