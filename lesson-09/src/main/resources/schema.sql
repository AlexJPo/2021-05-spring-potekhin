drop table if exists AUTHORS;
create table AUTHORS(
  id bigint primary key,
  name varchar(255)
);

drop table if exists GENRES;
create table GENRES(
  id bigint primary key,
  title varchar(255)
);

drop table if exists COMMENTS;
create table COMMENTS(
  id bigint primary key,
  text varchar(255)
);

drop table if exists BOOKS;
create table BOOKS(
  id bigint primary key,
  title varchar(255)
);

drop table IF exists BOOKS_AUTHORS;
create table BOOKS_AUTHORS(
    book_id bigint references BOOKS(id),
    author_id bigint references AUTHORS(id),
    primary key (book_id, author_id)
);

drop table IF exists BOOKS_GENRES;
create table BOOKS_GENRES(
    book_id bigint references BOOKS(id),
    genre_id bigint references GENRES(id),
    primary key (genre_id, genre_id)
);

drop table IF exists BOOKS_COMMENTS;
create table BOOKS_COMMENTS(
    book_id bigint references BOOKS(id),
    comment_id bigint references GENRES(id) on delete cascade,
    primary key (book_id, comment_id)
);
