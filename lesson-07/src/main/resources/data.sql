insert into genres (id, title) values (1, 'Fantasy');
insert into genres (id, title) values (2, 'Detective');

insert into authors (id, name) values (1, 'Aleksey');
insert into authors (id, name) values (2, 'Sergey');

insert into books (id, title, author_id, genre_id) values (1, 'jdbc home work', 1, 2);
insert into books (id, title, author_id, genre_id) values (2, 'test book', 2, 1);


