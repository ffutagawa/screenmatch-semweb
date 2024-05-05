package br.com.alura.Screenmatch2.service;

public interface IConverteDados {
	
	<T> T obterDados (String json, Class<T> classe);

}
