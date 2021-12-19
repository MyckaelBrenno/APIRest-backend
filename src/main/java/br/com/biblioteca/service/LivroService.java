package br.com.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.main.Categoria_livros;
import br.com.biblioteca.main.Livros;
import br.com.biblioteca.repositories.LivroRepository;
import br.com.biblioteca.service.execption.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private CategoriaService categoriaService;

	public Livros findById(Integer id) {
		Optional<Livros> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Livros.class.getName()));

	}

	public List<Livros> findAll(Integer id_cat) {
		categoriaService.findById(id_cat);
		return repository.findAllByCategoria(id_cat);
	}

	public Livros update(Integer id, Livros obj) {
		Livros newObj = findById(id);
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	private void updateData(Livros newObj, Livros obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setAutor(obj.getAutor());
		newObj.setTexto(obj.getTexto());
		newObj.setStatus(obj.getStatus());
	}
	
	public Livros create(Integer id_cat, Livros obj) {
		obj.setId(null);
		Categoria_livros cat = categoriaService.findById(id_cat);
		obj.setCategoria(cat);
		return repository.save(obj);
		
	}

	public void delete(Integer id) {
		Livros obj = findById(id);
		repository.delete(obj);
		
	}

}
