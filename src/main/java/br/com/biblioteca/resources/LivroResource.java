package br.com.biblioteca.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.biblioteca.dto.LivroDto;
import br.com.biblioteca.main.Livros;
import br.com.biblioteca.service.LivroService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

	@Autowired
	private LivroService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Livros> findById(@PathVariable Integer id) {
		Livros obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<LivroDto>> findAll(
			@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat) {
		List<Livros> list = service.findAll(id_cat);
		List<LivroDto> listDTO = list.stream().map(obj -> new LivroDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		// http://localhost:8080/livros?categoria=1
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Livros> update(@PathVariable Integer id, @Valid @RequestBody Livros obj) {
		Livros newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Livros> updatePatch(@PathVariable Integer id, @Valid @RequestBody Livros obj) {
		Livros newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	@PostMapping
	public ResponseEntity<Livros> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,
			@Valid @RequestBody Livros obj) {
		Livros newObj = service.create(id_cat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}")
				.buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
