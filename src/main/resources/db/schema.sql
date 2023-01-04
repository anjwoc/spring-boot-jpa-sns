DROP TABLE public.users;

CREATE TABLE public.users (
  id bigserial NOT NULL,
  email varchar(255) NULL,
  user_name varchar(255) NULL,
  "password" varchar(255) NULL,
  "role" varchar(255) NULL,
  created_at timestamp(6) NULL,
  updated_at timestamp(6) NULL,
  deleted_at timestamp(6) NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id)
);

-- Column comments
-- COMMENT ON COLUMN public.users.created_at IS '생성 일자';
-- COMMENT ON COLUMN public.users.deleted_at IS '삭제 일자';
-- COMMENT ON COLUMN public.users.email IS '유저 이메일';
-- COMMENT ON COLUMN public.users."password" IS '유저 패스워드';
-- COMMENT ON COLUMN public.users."role" IS '유저 권한';
-- COMMENT ON COLUMN public.users.updated_at IS '수정 일자';
-- COMMENT ON COLUMN public.users.user_name IS '유저 이름';


DROP TABLE public.posts;

CREATE TABLE public.posts (
  id bigserial NOT NULL,
  title varchar(255) NULL, -- 게시글 제목
  "content" oid NULL, -- 게시글 내용
  user_id int8 NULL,
  created_at timestamp(6) NULL, -- 생성 일자
  updated_at timestamp(6) NULL, -- 수정 일자
  deleted_at timestamp(6) NULL, -- 삭제 일자
  CONSTRAINT posts_pkey PRIMARY KEY (id)
);

-- Column comments
-- COMMENT ON COLUMN public.posts."content" IS '게시글 내용';
-- COMMENT ON COLUMN public.posts.created_at IS '생성 일자';
-- COMMENT ON COLUMN public.posts.deleted_at IS '삭제 일자';
-- COMMENT ON COLUMN public.posts.title IS '게시글 제목';
-- COMMENT ON COLUMN public.posts.updated_at IS '수정 일자';


-- public.posts foreign keys
-- ALTER TABLE public.posts ADD CONSTRAINT fk5lidm6cqbc7u4xhqpxm898qme FOREIGN KEY (user_id) REFERENCES public.users(id);


DROP TABLE public.likes;

CREATE TABLE public.likes (
  id bigserial NOT NULL,
  user_id int8 NULL,
  post_id int8 NULL,
  created_at timestamp(6) NULL, -- 생성 일자
  updated_at timestamp(6) NULL, -- 수정 일자
  deleted_at timestamp(6) NULL, -- 삭제 일자
  CONSTRAINT likes_pkey PRIMARY KEY (id)
);
--
-- -- Column comments
-- COMMENT ON COLUMN public.likes.created_at IS '생성 일자';
-- COMMENT ON COLUMN public.likes.deleted_at IS '삭제 일자';
-- COMMENT ON COLUMN public.likes.updated_at IS '수정 일자';
--
--
-- -- public.likes foreign keys
-- ALTER TABLE public.likes ADD CONSTRAINT fknvx9seeqqyy71bij291pwiwrg FOREIGN KEY (user_id) REFERENCES public.users(id);
-- ALTER TABLE public.likes ADD CONSTRAINT fkry8tnr4x2vwemv2bb0h5hyl0x FOREIGN KEY (post_id) REFERENCES public.posts(id);




DROP TABLE public."comments";

CREATE TABLE public."comments" (
   id bigserial NOT NULL,
   "comment" varchar(255) NULL,
   user_id int8 NULL,
   post_id int8 NULL,
   created_at timestamp(6) NULL, -- 생성 일자
   updated_at timestamp(6) NULL, -- 수정 일자
   deleted_at timestamp(6) NULL, -- 삭제 일자
   CONSTRAINT comments_pkey PRIMARY KEY (id)
);