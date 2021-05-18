package com.thiago.ecommerce.resources;

import com.thiago.ecommerce.entities.Pagamento;
import com.thiago.ecommerce.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pagamentos")
public class PagamentoResource {
    @Autowired
    private PagamentoService service;

    @GetMapping
    public ResponseEntity<List<Pagamento>> findAll(){
        List<Pagamento> list =service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pagamento> findById(@PathVariable Long id){
        Pagamento obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/new")
    public ResponseEntity<Pagamento> insert(@RequestBody Pagamento obj){
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Pagamento> update(@RequestBody Pagamento obj, @PathVariable Long id){
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PatchMapping("/process/{id}")
    public ResponseEntity<Pagamento> process(@PathVariable Long id) throws IllegalAccessException {
        return Optional.ofNullable(service.process(id))
                .map(pagamento -> ResponseEntity.ok().body(pagamento))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/byClient/{id}")
    public ResponseEntity<List<Pagamento>> byClient(@PathVariable Long id){
        return Optional.ofNullable( service.byClient(id) )
                .map(pagamentos -> ResponseEntity.ok().body(pagamentos))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
