package br.com.adrianosb.veiculo;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j
@SpringBootApplication
public class VeiculoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeiculoApplication.class, args);
    }

}
