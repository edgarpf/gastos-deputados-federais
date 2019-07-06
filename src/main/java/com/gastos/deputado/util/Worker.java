package com.gastos.deputado.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Worker implements Runnable {

	private String url;
	private Elements results;
	
	private final Object lock = new Object();

	public Worker(String url) {
		this.url = url;
	}

	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect(this.url).userAgent("Mozilla/17.0").get();

			Elements links = doc.select(".grafico-imoveis__valor-area");
			links.addAll(doc.select("#GASTOS_DEPUTADO option[selected=selected]"));
			links.addAll(doc.select(".gastos__resumo-texto--destaque"));
			synchronized (lock) {
				this.results = links;
				lock.notifyAll();
			}
		} catch (IOException e) {
			System.err.println("Error while parsing: " + this.url);
			e.printStackTrace();
		}
	}

	public Elements waitForResults() throws InterruptedException {
		synchronized (lock) {
				while (this.results == null) {
					lock.wait();
				}
				return this.results;
		}
	}
}
