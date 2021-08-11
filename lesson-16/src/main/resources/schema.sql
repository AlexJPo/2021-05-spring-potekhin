drop table if exists books;
create table books(
  id bigserial primary key,
  title varchar(255)
);

drop table if exists authors;
create table authors(
  id bigserial primary key,
  name varchar(255)
);

drop table if exists genres;
create table genres(
  id bigserial primary key,
  title varchar(255)
);

drop table if exists comments;
create table comments(
  id bigserial primary key,
  text varchar(255),
  book_id bigint references books(id) on delete cascade
);

drop table if exists books_authors;
create table books_authors(
  id bigserial primary key,
  book_id bigint references books(id) on delete cascade,
  author_id bigint references authors(id)
);

drop table if exists books_genres;
create table books_genres(
  id bigserial primary key,
  book_id bigint references books(id) on delete cascade,
  genre_id bigint references genres(id)
);
