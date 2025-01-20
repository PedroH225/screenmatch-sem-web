package com.example.screenmatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.screenmatch.model.DadosEpisodio;
import com.example.screenmatch.model.DadosSerie;
import com.example.screenmatch.model.DadosTemporada;
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
		var json = cons.obterDados("https://www.omdbapi.com/?t=the+boys&apikey=6585022c");
		
		ConverteDados converteDados = new ConverteDados();
		DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
		
		System.out.println(dados);
		
		var jsonEp = cons.obterDados("https://www.omdbapi.com/?t=the+boys&season=1&episode=2&apikey=6585022c");

		DadosEpisodio dadosEp = converteDados.obterDados(jsonEp, DadosEpisodio.class);
		
		System.out.println(dadosEp);
		
		List<DadosTemporada> temporadas = new ArrayList<DadosTemporada>();
		
		for (int i = 1; i <= dados.totalTemporadas(); i++) {
			var jsonTemp = cons.obterDados("https://www.omdbapi.com/?t=the+boys&season=" + i +"&apikey=6585022c");

			DadosTemporada dadosTemp = converteDados.obterDados(jsonTemp, DadosTemporada.class);
			
			temporadas.add(dadosTemp);
		}
		
		temporadas.forEach(System.out::println);
		
	}

}
