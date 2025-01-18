package com.example.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.screenmatch.model.DadosSerie;
import com.example.screenmatch.service.ConsumoApi;
import com.example.screenmatch.service.ConverteDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var cons = new ConsumoApi();
		var json = cons.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		
		ConverteDados converteDados = new ConverteDados();
		DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
		
		System.out.println(dados);
	}

}
