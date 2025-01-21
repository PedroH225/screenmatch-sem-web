package com.example.screenmatch.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.screenmatch.model.DadosEpisodio;
import com.example.screenmatch.model.DadosSerie;
import com.example.screenmatch.model.DadosTemporada;
import com.example.screenmatch.model.Episodio;
import com.example.screenmatch.service.ConsumoApi;
import com.example.screenmatch.service.ConverteDados;

public class Principal {

	private Scanner sc = new Scanner(System.in);

	private final String ENDERECO = "https://www.omdbapi.com/?t=";

	private final String API_KEY = "&apikey=6585022c";

	public void exibirMenu() {
		System.out.println();
		System.out.println("Digite o nome da série: ");
		var nomeDaSerie = sc.nextLine();

		var cons = new ConsumoApi();
		var json = cons.obterDados(ENDERECO + nomeDaSerie.replace(" ", "+") + API_KEY);

		ConverteDados converteDados = new ConverteDados();
		DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);

		System.out.println();
		System.out.println("Série: ");
		System.out.println(dados);

		System.out.println();
		System.out.println("Temporadas:");

		List<DadosTemporada> temporadas = new ArrayList<DadosTemporada>();

		for (int i = 1; i <= dados.totalTemporadas(); i++) {
			var jsonTemp = cons.obterDados(ENDERECO + nomeDaSerie.replace(" ", "+") + "&season=" + i + API_KEY);

			DadosTemporada dadosTemp = converteDados.obterDados(jsonTemp, DadosTemporada.class);

			temporadas.add(dadosTemp);
		}

		temporadas.forEach(System.out::println);

		System.out.println();
		System.out.println("Episódios: ");

		temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

		System.out.println(); 
		System.out.println("Top 5 episódios:");

		List<DadosEpisodio> dadosEpisodios = temporadas.stream().flatMap(t -> t.episodios().stream())
				.collect(Collectors.toList());

		dadosEpisodios.stream().filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
				.sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed()).limit(5)
				.forEach(System.out::println);
		
		List<Episodio> episodios = temporadas.stream()
				.flatMap(t -> t.episodios().stream()
						.map(d -> new Episodio(t.numero(), d)))
				.collect(Collectors.toList());
		
		episodios.forEach(System.out::println);

	}
}





