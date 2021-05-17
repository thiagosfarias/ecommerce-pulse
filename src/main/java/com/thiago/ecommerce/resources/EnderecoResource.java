package com.thiago.ecommerce.resources;

import com.thiago.ecommerce.entities.Cliente;
import com.thiago.ecommerce.entities.Endereco;
import com.thiago.ecommerce.services.ClienteService;
import com.thiago.ecommerce.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

    @Autowired
    private EnderecoService service;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Endereco>> findAll(){
        return Optional.ofNullable( service.findAll() )
                .map(enderecos -> ResponseEntity.ok().body(enderecos))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //pesquisar tambem pelo id do cliente

    @GetMapping(value = "/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id){
        return Optional.ofNullable( service.findById(id) )
                .map(endereco -> ResponseEntity.ok().body(endereco))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping("/new/{id}")
    public ResponseEntity<Endereco> insert(@RequestBody Endereco obj, @PathVariable Long id){
        return Optional.ofNullable( service.insert(obj, id) )
                .map(endereco -> ResponseEntity.ok().body(endereco))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



}
