create table books_authors(
  id bigint primary key AUTO_INCREMENT,
  book_id bigint references books(id) on delete cascade,
  author_id bigint references authors(id)
);