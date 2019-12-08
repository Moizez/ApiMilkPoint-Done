package net.milkpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.milkpoint.model.Laticinio;
import net.milkpoint.repository.LaticinioRepository;

@Service
public class LaticinioService {
	
	@Autowired
	private LaticinioRepository repository;
	
	public Laticinio save(Laticinio Laticinio) {
        return repository.saveAndFlush(Laticinio);
    }

	public List<Laticinio> findAll(){
		return repository.findAll();
	}
	
	public Laticinio findOne(Long id) {
        return repository.getOne(id);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public Laticinio buscaLogin(String email) {
    	return repository.findByEmailIgnoreCaseContaining(email);
    }

}
