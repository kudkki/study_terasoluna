package com.example.todo.domain.repository;

import com.example.todo.domain.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//public class JpaTodoRepository extends JpaRepository<Todo, String> implements TodoRepository {

public interface JpaTodoRepository extends JpaRepository<Todo, String> {

    @Query("SELECT COUNT(t) FROM Todo t WHERE t.finished = :finished")
    long countByFinished(@Param("finished") boolean finished);
}
