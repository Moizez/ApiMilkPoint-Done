package net.milkpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.milkpoint.model.Deposito;
import net.milkpoint.repository.DepositoRepository;

@Service
public class DepositoService {
	
	@Autowired
	private DepositoRepository repository;
	
	public void save(Deposito deposito) {
		repository.save(deposito);
	}
	
	public List<Deposito> findAll() {
		return repository.findAll();
	}
	
	public Deposito findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
        repository.deleteById(id);
    }
	
	public List<Deposito> buscaPendentes() {	
		return repository.buscaPendentes();
	}
}
