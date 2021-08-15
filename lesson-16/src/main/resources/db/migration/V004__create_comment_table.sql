create table comments(
  id bigint primary key AUTO_INCREMENT,
  text varchar(255),
  book_id bigint references books(id) on delete cascade
);