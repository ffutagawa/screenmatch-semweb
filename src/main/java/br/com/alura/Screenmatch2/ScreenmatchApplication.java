package br.com.alura.Screenmatch2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.Screenmatch2.Principal.Principal;
import br.com.alura.Screenmatch2.respository.SerieRepository;


@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	
	@Autowired
    private SerieRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.exibeMenu();	
		
	}

}





























