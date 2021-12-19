package br.com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.main.Livros;

@Repository
public interface LivroRepository extends JpaRepository<Livros, Integer>{

	@Query("SELECT obj FROM Livros obj WHERE obj.categoria.id = :id_cat ORDER BY titulo")
	List<Livros> findAllByCategoria(@Param(value = "id_cat") Integer id_cat);

}
