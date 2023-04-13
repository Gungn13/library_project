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
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(full_name, year_of_born) VALUES(?, ?)", person.getFull_name(), person.getYear_of_born());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, year_of_born=? WHERE person_id=?", updatedPerson.getFull_name(),
                updatedPerson.getYear_of_born(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

        public List<Book> getBooksByPersonId(int id){
            return jdbcTemplate.query("SELECT * FROM book where person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
        }

    public Optional<Person> getPersonByFullName(String full_name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{full_name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

}