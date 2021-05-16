package com.thiago.controledeestoque.resources;

import com.thiago.controledeestoque.entities.Pagamento;
import com.thiago.controledeestoque.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
