# Library System API Design

## Technology Used
Spring Boot
Apache Derby in-momery database

## Database Design

Book
  bookId       (Referring to primary key as ISBN number),
  book_title   (Title of book),
  writer       (name of the writer)
  create_date  (first entry added in library system)

## API Design

### Create an entry for book in system.
POST : /library/create
Request :
{
    "bookId" : 1,
    "bookTitle" : "Book_1",
    "writer": "Writer_1"
}
Response :
{
    "bookId": 1,
    "bookTitle": "Book_1",
    "writer": "Writer_1",
    "createDate": "Sun Dec 13 21:25:43 MST 2020"
}
Description: If the book is already present in system with the same bookId, it will return the existing entry in table,


### Find book by bookId
GET : /library//find/{bookId}
Response :
{
    "bookId": 1,
    "bookTitle": "Book_1",
    "writer": "Writer_1",
    "createDate": "Sun Dec 13 21:25:43 MST 2020"
}
Response if not found : Book not found in system for bookId {bookId}

### Delete book entry by bookId
DELETE : /library/delete/{bookId}
Response : 200 Ok

### Update book entry for bookId
PUT : /library/update/{bookId}
Request:
{
    "bookTitle" : "Book_6",
    "writer": "Writer_6"
}
Response:
{
    "bookId": 5,
    "bookTitle": "Book_6",
    "writer": "Writer_6",
    "createDate": "Sun Dec 13 21:21:40 MST 2020"
}

### Find all the books in system
GET : /library/findAll
Response:
[
    {
        "bookId": 1,
        "bookTitle": "Book_1",
        "writer": "Writer_1",
        "createDate": "Sun Dec 13 22:06:03 MST 2020"
    },
    {
        "bookId": 2,
        "bookTitle": "Book_2",
        "writer": "Writer_2",
        "createDate": "Sun Dec 13 22:06:14 MST 2020"
    },
    {
        "bookId": 3,
        "bookTitle": "Book_3",
        "writer": "Writer_3",
        "createDate": "Sun Dec 13 22:06:24 MST 2020"
    }
]




