package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year_of_production) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear_of_production());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year_of_production=? WHERE book_id=?", updatedBook.getName(),
                updatedBook.getAuthor(), updatedBook.getYear_of_production(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public Optional<Person> getBookOwner(int book_id){
        return jdbcTemplate.query("SELECT Person.* FROM book join person ON person.person_id = book.person_id " +
                "WHERE book_id = ?", new Object[]{book_id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release(int book_id){
        jdbcTemplate.update("update book set person_id = null where book_id = ?", book_id);
    }

    public void assign(int book_id, Person selectedPerson){
        jdbcTemplate.update("update book set person_id = ? where book_id = ?", selectedPerson.getPerson_id(), book_id);
    }
}
