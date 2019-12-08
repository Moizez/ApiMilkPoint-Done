package net.milkpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.milkpoint.model.Retirada;
import net.milkpoint.repository.RetiradaRepository;

@Service
public class RetiradaService {

	@Autowired
	private RetiradaRepository repository;

	public void save(Retirada retirada) {
		repository.save(retirada);
	}

	public List<Retirada> findAll() {
		return repository.findAll();
	}

	public Retirada findOne(Long id) {
		return repository.getOne(id);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
