package br.com.alura.Screenmatch2.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo, 
		                 @JsonAlias("totalSeasons") Integer totalTemporadas, 
		                 @JsonAlias("imdbRating") String avaliacao,
						 @JsonAlias("Genre") String genero,
						 @JsonAlias("Actors") String Atores,
						 @JsonAlias("Poster") String poster,		 
						 @JsonAlias("Plot") String sinopse)
	{
	
}
