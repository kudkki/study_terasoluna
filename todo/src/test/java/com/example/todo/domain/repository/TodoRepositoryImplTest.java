package com.example.todo.domain.repository;

import com.example.todo.domain.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TodoRepositoryImplTest {

//    private final TodoRepositoryImpl todoRepositoryImpl;
    private final JpaTodoRepository todoRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TodoRepositoryImplTest(JpaTodoRepository jpaTodoRepository, JdbcTemplate jdbcTemplate){
        this.todoRepository  = jpaTodoRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    public void createTestData(){
        createTable(jdbcTemplate);
        createData(jdbcTemplate);
    }

    @Test
    void findByid_結果0件() {
//        Collection<Todo> actual = todoRepository.findAll();
//        System.out.println(actual.toString());
        // あるやつを引く
        Optional<Todo> actual;
        actual = todoRepository.findById("1");
        assertThat(actual.isPresent()).isTrue();

        // ないやつを引く
        actual = todoRepository.findById(String.valueOf("99"));
        assertThat(actual.isEmpty()).isTrue();
    }

    @Test
    void findByid_結果1件() {
        // 全データあり
        Optional<Todo> actual = todoRepository.findById("1");
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getTodoTitle()).isEqualTo("todotitle");
        assertThat(actual.get().getCreatedAt().toString()).isEqualTo("2023-04-01 10:00:00.0");
//
//        // finishedデータ
//        todoRepositoryImpl.findById(2);
//
//        // deletedデータ
//        todoRepositoryImpl.findById(3);
//
//        // not nullのみデータ
//        todoRepositoryImpl.findById(4);
    }
//
//    @Test
//    void findAll_結果全件(){
//        // 全件そろったか
//        todoRepositoryImpl.findAll();
//    }
//
//
//    @Test
//    void countByFinished_null(){
//        // arg null
//        todoRepositoryImpl.countByFinished();
//    }
//    @Test
//    void countByFinished_件数取得成功(){
//        // arg true
//        todoRepositoryImpl.countByFinished();
//
//        // arg false
//        todoRepositoryImpl.countByFinished();
//    }
//
//    @Test
//    void create_null(){
//        // 引数null
//        todoRepositoryImpl.create();
//    }
//
//    @Test
//    void create_1件作成成功(){
//        // titileあり
//        todoRepositoryImpl.create();
//
//        // titileぬけ
//        todoRepositoryImpl.create();
//
//        // not nullだけ
//        todoRepositoryImpl.create();
//    }
//
//    @Test
//    void update_null(){
//        // 引数null
//        todoRepositoryImpl.update();
//    }
//
//    @Test
//    void update_1件更新(){
//        // titileあり
//        todoRepositoryImpl.update();
//
//        // titileぬけ
//        todoRepositoryImpl.update();
//
//        // not nullだけ
//        todoRepositoryImpl.update();
//    }
//
//    @Test
//    void delete_1件削除失敗(){
//        // 存在しないIDで更新
//        todoRepositoryImpl.delete();
//    }
//
//    @Test
//    void delete_null(){
//        // 引数null
//        todoRepositoryImpl.delete();
//    }
//
//    @Test
//    void delete_1件更新(){
//        // titileあり
//        todoRepositoryImpl.delete();
//
//        // titileぬけ
//        todoRepositoryImpl.delete();
//
//        // not nullだけ
//        todoRepositoryImpl.delete();
//    }
//
//    @Test
//    void update_1件更新失敗(){
//        // 存在しないIDで更新
//        todoRepositoryImpl.delete();
//
//    }
//
    private static void createTable(JdbcTemplate jdbcTemplate) {
        String sql = "DROP TABLE IF EXISTS todo";
        jdbcTemplate.execute(sql);

        sql = "CREATE TABLE todo(todo_id VARCHAR(36) PRIMARY KEY, todo_title VARCHAR(30), finished BOOLEAN, created_at TIMESTAMP)";
        jdbcTemplate.execute(sql);

    }

    private static void createData(JdbcTemplate jdbcTemplate) {

        String sql = "INSERT INTO todo (todo_id, todo_title, finished, created_at) VALUES ('1', 'todotitle', 'true', '2023-04-01 10:00:00')";
        int result_num = jdbcTemplate.update(sql);
        System.out.println(result_num);



    }

}
