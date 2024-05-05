package br.com.alura.Screenmatch2.Principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.type.CollectionType;

import br.com.alura.Screenmatch2.model.DadosSerie;
import br.com.alura.Screenmatch2.model.DadosTemporada;
import br.com.alura.Screenmatch2.model.Serie;
import br.com.alura.Screenmatch2.respository.SerieRepository;
import br.com.alura.Screenmatch2.service.ConsumoApi;
import br.com.alura.Screenmatch2.service.ConverteDados;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=3bc510b1";
    private List<DadosSerie> dadosSeries = new ArrayList<DadosSerie>();
   
    private SerieRepository repositorio;
	public Principal(SerieRepository repositorio) {
		this.repositorio = repositorio;
	}

	public void exibeMenu() {
    	var opcao = -1;
    	while(opcao != 0) {
        var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar Séries buscadas
                
                0 - Sair                                 
                """;

        System.out.println(menu);
        opcao = leitura.nextInt();
        leitura.nextLine();

        switch (opcao) {
            case 1:
                buscarSerieWeb();
                System.out.println("\n");
                break;
            case 2:
                buscarEpisodioPorSerie();
                break;
            case 3:
            	listarSeriesBuscadas();
            	System.out.println("\n");
            	break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida");
        	}
    	}
    }	

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
//        dadosSeries.add(dados);
        Serie serie = new Serie(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
    
    private void listarSeriesBuscadas() {
    	
    	List<Serie> series = repositorio.findAll();
    	series.stream()
    		.sorted(Comparator.comparing(Serie::getGenero))
    		.forEach(System.out::println);
    	
    }
}


























