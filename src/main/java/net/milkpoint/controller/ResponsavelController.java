package net.milkpoint.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.milkpoint.model.Responsavel;
import net.milkpoint.service.ResponsavelService;

@Controller
@RequestMapping("/responsavel")
public class ResponsavelController {

	@Autowired
	private ResponsavelService responsavelService;

	@GetMapping("/add")
	public ModelAndView add(Responsavel responsavel) {

		ModelAndView mv = new ModelAndView("responsavel/form");
		mv.addObject("responsavel", responsavel);

		return mv;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Responsavel responsavel, BindingResult result) throws IOException {

		if (result.hasErrors()) {
			return add(responsavel);
		}

		responsavelService.save(responsavel);

		return findAll();
	}

	@GetMapping("/listar")
	public ModelAndView findAll() {

		ModelAndView mv = new ModelAndView("responsavel/listar");
		mv.addObject("responsaveis", responsavelService.findAll());

		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {

		return add(responsavelService.findOne(id));
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {

		responsavelService.delete(id);

		return findAll();
	}

}
