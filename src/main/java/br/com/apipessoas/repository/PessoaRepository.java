package br.com.apipessoas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.apipessoas.model.Pessoa;

public interface PessoaRepository  extends MongoRepository<Pessoa, Integer>{
	
}
