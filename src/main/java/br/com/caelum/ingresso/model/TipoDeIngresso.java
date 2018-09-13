package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.descontos.Desconto;
import br.com.caelum.ingresso.model.descontos.DescontoParaBancos;
import br.com.caelum.ingresso.model.descontos.DescontoParaEstudante;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public enum TipoDeIngresso {
	
	INTEIRO(new SemDesconto()),
	ESTUDANTE(new DescontoParaEstudante()),
	BANCO(new DescontoParaBancos());
	
	private final Desconto desconto;
	
	TipoDeIngresso(Desconto desconto){
		this.desconto = desconto;	
	}
	
	public BigDecimal aplicaDesconto(BigDecimal valor){
		return desconto.aplicarDescontoSobre(valor);
	}
	
	public String getDescricao(){
		return desconto.getDescricao();
	}

}
