package com.gastos.deputado.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gastos.deputado.dto.Deputado;
import com.gastos.deputado.repository.DeputadoRepository;
import com.gastos.deputado.util.Worker;

@Service
public class GastosDeputadosScraperService {

	private Logger logger = LoggerFactory.getLogger(GastosDeputadosScraperService.class);
	
	@Value("${maxRequests}")
	private int maxRequests =50;

	@Value("${numberOfLoops}")
	private int numberOfLoops;

	@Value("${restOfDivision}")
	private int restOfDivision;

	@Value("${urlGlobal}")
	private String urlGlobal;

	@Value("${urlDeputy}")
	private String urlDeputado;

	@Autowired
	private DeputadoRepository deputyRepository;
	
	public void saveDeputiesInMemory() throws Exception {
		deputyRepository.saveAll(scraperDeputies());
	}

	public List<Deputado> scraperDeputies() throws Exception {
		List<Deputado> listDeputies = new ArrayList<Deputado>();
		Elements deputies = Jsoup.connect(urlGlobal).get().select("#GASTOS_DEPUTADO option[data-legislatura=56]");

		for (int i = 0; i <= numberOfLoops; i++) {
			logger.info("loop " + i + " de " + numberOfLoops);
			int limit = (i == numberOfLoops ? restOfDivision : maxRequests);

			List<Worker> workers = new ArrayList<>(limit);

			gerarThreads(deputies, i, limit, workers);
			preencherListaDeDeputados(listDeputies, i, limit, workers);
		}

		return listDeputies;
	}

	private void gerarThreads(Elements deputies, int i, int limit, List<Worker> workers) {
		for (int j = 0; j < limit; j++) {
			Worker worker = new Worker(urlDeputado + deputies.get(i * maxRequests + j).attr("value"));
			workers.add(worker);
			new Thread(worker).start();
		}
	}

	private void preencherListaDeDeputados(List<Deputado> listDeputies, int i, int limit, List<Worker> workers)
			throws InterruptedException {
		for (int j = 0; j < limit; j++) {
			Elements spendings = workers.get(j).waitForResults();
			int index = i * maxRequests + j;
			listDeputies.add(new Deputado());
			listDeputies.get(index).setRenunciouAuxilioMoradia(spendings.get(3).text().contains("1"));
			listDeputies.get(index).setNome(spendings.get(4).text());
			listDeputies.get(index).setCotaParlamentar(spendings.size() < 6 ? null : spendings.get(5).text());
			listDeputies.get(index).setVerbaGabinete(spendings.size() < 7 ? null : spendings.get(6).text());
			listDeputies.get(index).setAuxilioMoradia(spendings.size() < 8 ? null : spendings.get(7).text());
		}
	}
}
