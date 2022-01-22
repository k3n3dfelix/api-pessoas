package br.com.apipessoas.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.apipessoas.model.Pessoa;
import br.com.apipessoas.repository.PessoaRepository;

@RestController
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository;
	
	@PostMapping("/adicionarPessoa")
	public String savePessoa(@RequestBody Pessoa pessoa) {
		repository.save(pessoa);
		return "Adicionado pessoa com id: " + pessoa.getId();
	}
	
	@GetMapping("/listarPessoas")
	public List<Pessoa> getPessoas(){
		return repository.findAll();
	}
	
	@GetMapping("/listarPessoa/{id}")
	public Optional<Pessoa> getPessoa(@PathVariable int id){
		return repository.findById(id);
	}
	
	@DeleteMapping("/deletarPessoa/{id}")
	public String deletarPessoa(@PathVariable int id) {
		repository.deleteById(id);
		return "Pessoas deletada com o id: " + id;
	}
}
