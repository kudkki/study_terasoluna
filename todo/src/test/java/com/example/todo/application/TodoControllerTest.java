package com.example.todo.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.HashMap;
import static org.assertj.core.api.Assertions.assertThat;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TodoControllerTest {
////    @Value(value = "${local.server.port}")
//    @LocalServerPort
//    public static int port;
//    public static int port = 8080;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TodoControllerTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    public void createTestData(){
        createTable(jdbcTemplate);
        createData(jdbcTemplate);
    }

    @Test
    void getTest_todolist(){
        String endpoint = "todo/list";
        String response = httpget(endpoint, null);
        // todo/list一覧画面の起動時にちゃんと取れるか
        assertThat(response).isEqualTo("status:200" +
                " <!DOCTYPE HTML><html><head>    <title>Getting Started: Serving Web Content</title>    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />    <style type=\"text/css\">        .strike {            text-decoration: line-through;        }        .inline {            display: flex;            align-items: center;            list-style-type: disc;        }        .inline_in {            margin: 3px;            padding: 3px;        }        .alert {            border: 1px solid;        }        .alert-error {            background-color: #c60f13;            border-color: #970b0e;            color: white;        }        .alert-success {            background-color: #5da423;            border-color: #457a1a;            color: white;        }        .text-error {            color: #c60f13;        }    </style></head><body><h1>Todo List</h1><p></p><hr /><div id=\"todoForm\">    <form action=\"/todo/create\" method=\"post\">        <label>Title : </label>        <input type=\"text\" id=\"todoTitle\" name=\"todoTitle\" value=\"\" />        <input type=\"submit\" value=\"登録\">    </form></div><div id=\"todoList\">    <ul>        <li class=\"inline\">                            <span class=\"strike\">                    <p class=\"inline_in\">todotitle</p>                </span>                                    <form action=\"/todo/delete\" class=\"inline_in\" method=\"post\">                <input type=\"hidden\" name=\"todoId\" value=\"1\" />                <input type=\"submit\" value=\"Delete\">            </form>        </li>    </ul></div></body></html>");
    }

    @Test
    void postFinish_todolist(){

    }

    @Test
    void postCreate_todolist(){

    }

    @Test
    void postDelete_todolist(){
        
    }


    private static String httpget(String endpoint, HashMap<String, String> queryParam){
        StringBuilder sb = new StringBuilder();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromPath(endpoint)
                .host("localhost")
                .port(8080);

        URL url;
        try {
            if(queryParam == null){
                url = new URL("http:" + builder.toUriString());
            } else {
                for (String key : queryParam.keySet()){
                    builder.queryParam(key, queryParam.get(key));
                }
                url = new URL("http:" + builder.toUriString());
            }
            System.out.println(url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("charset", "UTF-8");

            conn.connect();

            sb.append("status:");
            sb.append(conn.getResponseCode());
            sb.append(" ");

            if(conn.getResponseCode() == 200){
                try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))){
                    String line;
                    while((line = br.readLine()) != null){
                        sb.append(line);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }

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
