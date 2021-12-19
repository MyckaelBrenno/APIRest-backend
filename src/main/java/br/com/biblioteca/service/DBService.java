package br.com.biblioteca.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.main.Categoria_livros;
import br.com.biblioteca.main.Cliente;
import br.com.biblioteca.main.Livros;
import br.com.biblioteca.repositories.CategoriaRepository;
import br.com.biblioteca.repositories.ClienteRepository;
import br.com.biblioteca.repositories.LivroRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	public void instanciaDB() {
		
		Categoria_livros cat1 = new Categoria_livros(null, "Informática", "Ficção");
		
		Cliente cliente = new Cliente(null, "Myckael", "Euclides Ferreira de Carvalho", "Jardim Cidade Universitária", "João Pessoa", "PB", cat1);
		
		Livros l1 = new Livros(null, "O Codificador Limpo", "Robert Cecil", "Lorem Ipsum", "0", cat1);
		
		cat1.getLivros().addAll(Arrays.asList(l1));
		cat1.getCliente().addAll(Arrays.asList(cliente));
		
		
		
		this.categoriaRepository.saveAll(Arrays.asList(cat1));
		this.livroRepository.saveAll(Arrays.asList(l1));
		this.clienteRepository.save(cliente);
	}

}
