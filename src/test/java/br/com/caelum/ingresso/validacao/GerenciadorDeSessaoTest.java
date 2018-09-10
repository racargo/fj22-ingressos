package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {
	
	private Filme rogueOne;
	private Sala Sala3D;
	private Sessao sessaoDasDez;
	private Sessao sessaoDasTreze;
	private Sessao sessaoDasDezoito;
	
	@Before
	public void preparaSessoes(){
		this.rogueOne = new Filme ("Rogue One", Duration.ofMinutes(120), "SCI-FI");
		this.Sala3D = new Sala ("Legal");
		
		this.sessaoDasDez = new Sessao(LocalTime.parse("10:00:00"), rogueOne, Sala3D);
		this.sessaoDasTreze = new Sessao(LocalTime.parse("13:00:00"), rogueOne, Sala3D);
		this.sessaoDasDezoito = new Sessao(LocalTime.parse("18:00:00"), rogueOne, Sala3D);
		
	}
	
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
	
	/*@Test
	public void deveConflitarQuandoFimDaAtualEstaDentroDaAntiga(){
		
		List<Sessao> listaDeSessoes = new Arrays.asList(sessaoDasDez);
		
		Sessao sessao = new Sessao(sessaoDasDez.getHorario().minusHours(1), rogueOne, Sala3D);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(listaDeSessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));
		
		
	}*/
	

}









