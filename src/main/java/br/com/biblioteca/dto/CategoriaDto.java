package br.com.biblioteca.dto;

import java.io.Serializable;

import br.com.biblioteca.main.Categoria_livros;

public class CategoriaDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String info;
	
	public CategoriaDto() {
		super();
	}
	public CategoriaDto(Categoria_livros obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.info = obj.getInfo();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	

}
