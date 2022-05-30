package DB1CRUDcvalidacion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class CrudCValidacionApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudCValidacionApplication.class, args);
    log.warn("| | | | | | | | | | ----- APP READY ----- | | | | | | | | | |");
  }
}
