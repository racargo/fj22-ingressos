package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {

	private List<Sessao> listaDeSessoes;

	public GerenciadorDeSessao(List<Sessao> listaDeSessoes) {
		this.listaDeSessoes = listaDeSessoes;
		// TODO Auto-generated constructor stub
	}

					public boolean cabe(Sessao novaSessao) {
								
							
							return listaDeSessoes.stream().noneMatch(x -> conflita(x,novaSessao));
						
					}
	

	private boolean conflita(Sessao antigaSessao, Sessao novaSessao) {
		LocalDateTime inicioSessaoAntiga = antigaSessao.getHorario().atDate(LocalDate.now());
		LocalDateTime finalSessaoAntiga = antigaSessao.getHorarioTermino().atDate(LocalDate.now());
		LocalDateTime inicioSessaoNova = novaSessao.getHorario().atDate(LocalDate.now());
		
		 if (inicioSessaoNova.isAfter(inicioSessaoAntiga) && inicioSessaoNova.isBefore(finalSessaoAntiga))
			 return true;
		
		return false;
		
	}

}
