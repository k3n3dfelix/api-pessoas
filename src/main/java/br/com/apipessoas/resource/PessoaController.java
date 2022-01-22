package br.com.apipessoas.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/editarPessoa/{id}")
	public ResponseEntity<Pessoa> getPessoaEdit(@PathVariable int id, @RequestBody Pessoa pessoa){
		 Optional<Pessoa> pessoaData = repository.findById(id);
		 
		 if (pessoaData.isPresent()) {
		 Pessoa _pessoa = pessoaData.get();
		 _pessoa.setNome(pessoa.getNome());
		 _pessoa.setSexo(pessoa.getSexo());
		 _pessoa.setEmail(pessoa.getEmail());
		 _pessoa.setData_nascimento(pessoa.getData_nascimento());
		 _pessoa.setNaturalidade(pessoa.getNaturalidade());
		 _pessoa.setNacionalidade(pessoa.getNacionalidade());
		 _pessoa.setCpf(pessoa.getCpf());
		 return new ResponseEntity<Pessoa>(repository.save(_pessoa), HttpStatus.OK);
		 }
		 else {
			    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deletarPessoa/{id}")
	public String deletarPessoa(@PathVariable int id) {
		repository.deleteById(id);
		return "Pessoas deletada com o id: " + id;
	}
}
