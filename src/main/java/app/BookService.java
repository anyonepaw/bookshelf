package app;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final JdbcTemplate jdbcTemplate;

    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    * Данные, которые должны быть в таблице:
    • id – идентификатор книги в БД;
    • title – название книги. Можно использовать тип VARCHAR(100);
    • description – краткое описание о чем книга. Можно использовать тип VARCHAR(255);
    • author – фамилия и имя автора. Можно использовать тип VARCHAR(100);
    • isbn – ISBN книги. Можно использовать тип VARCHAR(20);
    • printYear – в каком году напечатана книга (INT);
    • readAlready – читал ли кто-то эту книгу. Это булево поле.
    * */


}
