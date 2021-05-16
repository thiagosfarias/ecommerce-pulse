package com.thiago.controledeestoque.resources;

import com.thiago.controledeestoque.services.ClienteService;
import com.thiago.controledeestoque.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Cliente> update(@RequestBody Cliente obj, @PathVariable Long id){
        Optional<Cliente> oldCliente = Optional.ofNullable(service.findById(id));

        if(oldCliente.isPresent()){

            Cliente cliente = oldCliente.get();

            cliente.setNome(obj.getNome());
            cliente.setnDocumento(obj.getnDocumento());
            cliente.setTelefone(obj.getTelefone());
            cliente.setDataNascimento(obj.getDataNascimento());
            cliente.setDataNascimento(obj.getDataNascimento());

            service.update(cliente);

            return ResponseEntity.ok().body(cliente);
        }

        return ResponseEntity.notFound().build();

    }
}
