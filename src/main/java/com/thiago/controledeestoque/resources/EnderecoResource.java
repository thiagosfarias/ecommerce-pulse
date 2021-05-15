package com.thiago.controledeestoque.resources;

import com.thiago.controledeestoque.entities.Endereco;
import com.thiago.controledeestoque.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public ResponseEntity<List<Endereco>> findAll(){
        List<Endereco> list =service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //pesquisar tambem pelo id do cliente

    @GetMapping(value = "/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id){
        Endereco obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/new")
    public ResponseEntity<Endereco> insert(@RequestBody Endereco obj){
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
}
