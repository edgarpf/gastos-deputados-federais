package com.gastos.deputado;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.gastos.deputado.service.GastosDeputadosScraperService;

@SpringBootApplication
@EnableScheduling
public class DeputadoApplication {

	@Autowired
	private GastosDeputadosScraperService deputiesSpendingScraper;
	
    private static final Logger log = LoggerFactory.getLogger(DeputadoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DeputadoApplication.class, args);
	}
	
	@PostConstruct
	@Scheduled(cron = "0 0 0 * * *")
    private void init() throws Exception {
		log.info("Iniciando Scrapper");
		deputiesSpendingScraper.saveDeputiesInMemory();
		log.info("Scrapping terminado!");
    }
}
