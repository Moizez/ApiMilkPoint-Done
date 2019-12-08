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

import net.milkpoint.model.Laticinio;
import net.milkpoint.service.LaticinioService;

@Controller
@RequestMapping("/laticinio")
public class LaticinioController {

	@Autowired
	private LaticinioService laticinioService;

	@RequestMapping("/add")
	public ModelAndView add(Laticinio laticinio) {
		ModelAndView mv = new ModelAndView("laticinio/form");
		mv.addObject("laticinio", laticinio);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Laticinio laticinio, BindingResult result) throws IOException {

		if (result.hasErrors()) {
			return add(laticinio);
		}

		laticinioService.save(laticinio);

		return findAll();
	}

	@GetMapping("/listar")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("laticinio/listar");
		mv.addObject("laticinios", laticinioService.findAll());
		return mv;
	}

	@GetMapping("/edit/{id}")
	private ModelAndView edit(@PathVariable("id") Long id) {
		Laticinio laticinio = laticinioService.findOne(id);
		return add(laticinio);
	}

	@GetMapping("/delete/{id}")
	private ModelAndView delete(@PathVariable("id") Long id) {
		laticinioService.delete(id);
		return findAll();
	}

}
