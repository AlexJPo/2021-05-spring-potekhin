insert into books (id, title) values (1, 'jdbc home work'), (2, 'test book');

insert into genres (id, title)
values (1, 'Fantasy'), (2, 'Detective'), (3, 'Romantic'), (4, 'Comedy');

insert into authors (name)
values ('Aleksey'), ('Sergey'), ('Dmitriy'), ('Kira');

insert into comments (id, text, book_id)
values (1, 'Good book', 1), (2, 'Very bored', 1), (3, 'Amazing', 1), (4, 'I love it!', 2);


insert into books_authors(id, book_id, author_id)
values (1, 1, 2), (2, 1, 3),
       (3, 2, 1), (4, 2, 2), (5, 2, 4);

insert into books_genres(id, book_id, genre_id)
values (1, 1, 1), (2, 1, 2),
       (3, 2, 1), (4, 2, 3), (5, 2, 4)
