package br.com.biblioteca.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.dto.LivroDto;
import br.com.biblioteca.main.Livros;
import br.com.biblioteca.service.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {
	
	@Autowired
	private LivroService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livros> findById(@PathVariable Integer id){
		Livros obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<LivroDto>> findAll(@RequestParam (value = "categoria", defaultValue = "0") Integer id_cat) {
		List<Livros> list = service.findAll(id_cat);
		List<LivroDto> listDTO = list.stream().map(obj -> new LivroDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		//http://localhost:8080/livros?categoria=1
	}
}
