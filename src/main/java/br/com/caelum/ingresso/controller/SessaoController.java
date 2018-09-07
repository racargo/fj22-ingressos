package br.com.caelum.ingresso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

@Controller
public class SessaoController {

	@Autowired
	private SessaoDao sessaoDao;

	@Autowired
	private SalaDao salaDao;

	@Autowired
	private FilmeDao filmeDao;


	@GetMapping("admin/sessao")
	public ModelAndView form(Integer salaId, SessaoForm form) {

		form.setSalaId(salaId);

		ModelAndView modelAndView = new ModelAndView("sessao/sessao");
		
		modelAndView.addObject("sala", salaDao.findOne(salaId));
		modelAndView.addObject("filmes", filmeDao.findAll());
		modelAndView.addObject("form", form);
		return modelAndView;
		
	}


	@PostMapping("admin/sessao")
	@Transactional
	public ModelAndView salva(@Valid SessaoForm form, BindingResult result){
		if( result.hasErrors() ) return form(form.getSalaId(),form);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");
		Sessao sessao = form.toSessao(salaDao, filmeDao);
		Sala sala = salaDao.findOne(form.getSalaId());
		GerenciadorDeSessao gds = new GerenciadorDeSessao(sessaoDao.buscaSessoesDaSala(sala));
		if(gds.cabe(sessao)) {
			sessaoDao.save(sessao);	
		}else {
			return form(form.getSalaId(),form);
		}

		
		return new ModelAndView("redirect:/admin/sala/"+form.getSalaId() + "/sessoes");
	}
	
	
	public SalaDao getSalaDao() {
		return salaDao;
	}

	public void setSalaDao(SalaDao salaDao) {
		this.salaDao = salaDao;
	}

	public FilmeDao getFilmeDao() {
		return filmeDao;
	}

	public void setFilmeDao(FilmeDao filmeDao) {
		this.filmeDao = filmeDao;
	}
	
	

}
