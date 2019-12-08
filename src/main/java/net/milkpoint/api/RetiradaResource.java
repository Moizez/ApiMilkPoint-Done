package net.milkpoint.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.milkpoint.model.Laticinio;
import net.milkpoint.model.Retirada;
import net.milkpoint.model.Tanque;
import net.milkpoint.service.LaticinioService;
import net.milkpoint.service.RetiradaService;
import net.milkpoint.service.TanqueService;

@RestController
@RequestMapping("/api")
public class RetiradaResource {

	@Autowired
	private RetiradaService service;

	@Autowired
	private TanqueService tanqueService;

	@Autowired
	private LaticinioService laticinioService;

	public ResponseEntity<Retirada> add(Retirada retirada) {

		if (retirada != null) {
			service.save(retirada);
			return ResponseEntity.ok(retirada);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/retirada")
	public ResponseEntity<Retirada> retirada(@RequestParam("quantidade") float quantidade,
			@RequestParam("idLat") Long idLat, @RequestParam("idTanque") Long idTanque) {
		Tanque tanque = tanqueService.findOne(idTanque);
		Laticinio laticinio = laticinioService.findOne(idLat);
		Retirada retirada = new Retirada();
		retirada.setLaticinio(laticinio);
		retirada.setTanque(tanque);
		retirada.setQuantidade(quantidade);
		tanqueService.save(tanque);
		return add(retirada);
	}
	
	@PostMapping("/retirada/confirmacao")
	public ResponseEntity<Retirada> confirmacao(@RequestParam("confirmacao") boolean confirmacao, 
			@RequestParam("idRetirada") Long idRetirada) {
		
		Retirada retirada = service.findOne(idRetirada);
		
		if(retirada != null) {
			retirada.setConfirmacao(confirmacao);
			
			if(confirmacao) {
				Tanque tanque = retirada.getTanque();
				tanque.setQtdAtual(tanque.getQtdAtual() - retirada.getQuantidade());
				tanque.setQtdRestante(tanque.getQtdRestante() + retirada.getQuantidade());
			}
			service.save(retirada);
			return ResponseEntity.ok(retirada);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/retirada/listatodos")
	public List<Retirada> listAll() {
		return service.findAll();
	}
	
}
