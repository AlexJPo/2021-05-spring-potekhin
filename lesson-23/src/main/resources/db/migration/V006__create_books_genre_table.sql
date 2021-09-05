create table books_genres(
  id bigint primary key AUTO_INCREMENT,
  book_id bigint references books(id) on delete cascade,
  genre_id bigint references genres(id)
);