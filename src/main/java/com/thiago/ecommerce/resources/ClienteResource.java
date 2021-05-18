package com.thiago.ecommerce.resources;

import com.thiago.ecommerce.entities.Endereco;
import com.thiago.ecommerce.services.ClienteService;
import com.thiago.ecommerce.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>>findAll(){
        List<Cliente> list =service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        return Optional
                .ofNullable( service.findById(id) )
                .map( client -> ResponseEntity.ok().body(client) )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping("/new")
    public ResponseEntity<Cliente> insert(@RequestBody Cliente obj){
        return Optional.ofNullable( service.insert(obj) )
                .map(cliente -> ResponseEntity.ok().body(cliente))
                .orElseGet(() -> ResponseEntity.status(400).build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Cliente> update(@RequestBody Cliente obj, @PathVariable Long id){
        return Optional.ofNullable( service.update(obj, id) )
                .map(cliente -> ResponseEntity.ok().body(cliente))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/enderecos")
    public ResponseEntity<Set<Endereco>> findAllEnderecos(@PathVariable Long id){
        return Optional
                 .ofNullable( service.findById(id) )
                .map(client -> ResponseEntity.ok().body(client.getEnderecos()))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
