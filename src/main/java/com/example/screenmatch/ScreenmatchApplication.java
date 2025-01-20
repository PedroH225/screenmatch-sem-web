package com.example.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.screenmatch.principal.Principal;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		
		principal.exibirMenu();
		
		
		/*
		 * List<DadosTemporada> temporadas = new ArrayList<DadosTemporada>();
		 * 
		 * for (int i = 1; i <= dados.totalTemporadas(); i++) { var jsonTemp =
		 * cons.obterDados("https://www.omdbapi.com/?t=the+boys&season=" + i
		 * +"&apikey=6585022c");
		 * 
		 * DadosTemporada dadosTemp = converteDados.obterDados(jsonTemp,
		 * DadosTemporada.class);
		 * 
		 * temporadas.add(dadosTemp); }
		 * 
		 * temporadas.forEach(System.out::println);
		 */
		
	}

}
