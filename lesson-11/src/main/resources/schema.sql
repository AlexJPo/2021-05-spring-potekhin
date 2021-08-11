drop table if exists books_authors;
drop table if exists books_genres;

drop table if exists comments;
drop table if exists genres;
drop table if exists authors;

drop table if exists books;

create table books(
  id bigserial primary key,
  title varchar(255)
);

create table authors(
  id bigserial primary key,
  name varchar(255)
);

create table genres(
  id bigserial primary key,
  title varchar(255)
);

create table comments(
  id bigserial primary key,
  text varchar(255),
  book_id bigint references books(id) on delete cascade
);

create table books_authors(
  id bigserial primary key,
  book_id bigint references books(id) on delete cascade,
  author_id bigint references authors(id)
);

create table books_genres(
  id bigserial primary key,
  book_id bigint references books(id) on delete cascade,
  genre_id bigint references genres(id)
);
