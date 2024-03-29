package net.milkpoint.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.milkpoint.model.Laticinio;
import net.milkpoint.service.LaticinioService;

@RestController
@RequestMapping("/api")
public class LaticinioResource {

	@Autowired
	private LaticinioService laticinioService;

	@PostMapping("/laticinio")
	public ResponseEntity<Laticinio> add(@RequestBody @Valid Laticinio laticinio) {
		if (laticinio != null) {
			laticinioService.save(laticinio);
			return new ResponseEntity<Laticinio>(laticinio, HttpStatus.CREATED);
		}
		return new ResponseEntity<Laticinio>(laticinio, HttpStatus.CONFLICT);

	}

	@GetMapping("/laticinio")
	public List<Laticinio> listar() {
		return laticinioService.findAll();
	}

	@GetMapping("/laticinio/{id}")
	public ResponseEntity<Laticinio> buscar(@PathVariable Long id) {
		Laticinio Laticinio = laticinioService.findOne(id);

		if (Laticinio == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Laticinio);
	}

	@PutMapping("/laticinio/{id}")
	public ResponseEntity<Laticinio> update(@PathVariable Long id, @Valid @RequestBody Laticinio Laticinio) {
		Laticinio prod = laticinioService.findOne(id);

		if (prod == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(Laticinio, prod, "id");
		prod = laticinioService.save(prod);
		return ResponseEntity.ok(prod);

	}

	@DeleteMapping("/laticinio/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Laticinio Laticinio = laticinioService.findOne(id);

		if (Laticinio == null) {
			return ResponseEntity.notFound().build();
		}
		laticinioService.delete(id);
		return ResponseEntity.noContent().build();

	}

}
