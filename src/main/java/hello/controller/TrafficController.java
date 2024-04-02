package hello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class TrafficController {

    //CPU 부하를 주는 컨트롤러
    @GetMapping("cpu")
    public String cpu() {
        log.info("cpu");
        long value = 0;
        for (long i = 0; i< 10000000000000L; i++) {
            value++;
        }
        return "ok value=" + value;
    }

    private List<String> list = new ArrayList<>();

    //JVM 메모리 사용량 확인
    @GetMapping("jvm")
    public String jvm() {
        log.info("jvm");
        for (int i=0; i< 1000000; i++) {
            list.add("hello jvm!" + i);
        }
        return "ok";
    }

    //JDBC 커넥션 고갈
    @Autowired
    DataSource dataSource;

    @GetMapping("jdbc")
    public String jdbc() throws SQLException {
        log.info("jdbc");
        Connection conn = dataSource.getConnection();
        log.info("connection info={}", conn);
        //conn.close(); //커넥션을 닫지 않는다.
        return "ok";
    }

    //에러 로그 급증
    @GetMapping("/error-log")
    public String errorLog() {
        log.error("error log");
        return "error";
    }
}
