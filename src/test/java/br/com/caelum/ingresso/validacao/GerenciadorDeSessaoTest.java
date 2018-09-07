package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {
	
	@Test
	public void deveConflitarQuandoOInicioDaAtualEstaDentroDaAntiga() {
		// Setup
		Filme filme = new Filme("Matriz", Duration.ofMinutes(180), "Ação");
		Sala sala = new Sala("Legal");
		Sessao novaSessao = new Sessao(LocalTime.parse("15:00"), filme, sala);
		Sessao sessaoAntiga = new Sessao(LocalTime.parse("14:00"), filme, sala);
		
		
		List<Sessao> listaDeSessoes = new ArrayList<>();
		listaDeSessoes.add(sessaoAntiga);
		
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(listaDeSessoes);
		Assert.assertFalse(gerenciador.cabe(novaSessao));
	}
	

}









