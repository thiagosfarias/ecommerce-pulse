package com.thiago.ecommerce.resources;

import com.thiago.ecommerce.entities.Cliente;
import com.thiago.ecommerce.entities.TipoPagamento;
import com.thiago.ecommerce.services.CarrinhoService;
import com.thiago.ecommerce.entities.Carrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/carrinhos")
public class CarrinhoResource {
    @Autowired
    private CarrinhoService service;

    @GetMapping
    public ResponseEntity<List<Carrinho>> findAll(){
        List<Carrinho> list =service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/new/{id}")
    public ResponseEntity<Carrinho> newCarrinho(@PathVariable Long id){
        return Optional.ofNullable( service.novoCarrinho(id) )
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Carrinho> findById(@PathVariable Long id){
        return Optional
                .ofNullable( service.findById(id) )
                .map( carrinho -> ResponseEntity.ok().body(carrinho) )
                .orElseGet( () -> ResponseEntity.status(404).build() );
    }

    @DeleteMapping("/deleteItem/{id}/{index}")
    public ResponseEntity<Carrinho> deleteItem(@PathVariable Long id, @PathVariable int index){
        return Optional
                .ofNullable( service.deleteItem(id, index) )
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PatchMapping("/newItem/{id}/{idProduto}/{quantidade}")
    public ResponseEntity<Carrinho> novoItem(@PathVariable Long id, @PathVariable Long idProduto, @PathVariable int quantidade){
        return Optional.ofNullable( service.novoItem(id, idProduto, quantidade) )
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("checkout/{id}")
    public ResponseEntity<Carrinho> checkout(@PathVariable Long id){
        return Optional.ofNullable( service.checkout(id) )
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("entrega/{id}/{idEndereco}/{idTransportadora}")
    public ResponseEntity<Carrinho> defineShippment(@PathVariable Long id, @PathVariable Long idEndereco, @PathVariable Long idTransportadora){
        return Optional.ofNullable( service.defineShippment(id, idEndereco, idTransportadora) )
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("pagar/{id}")
    public ResponseEntity<Carrinho> pay(@PathVariable Long id, @RequestBody TipoPagamento tipoPagamento){
        return Optional.ofNullable(service.pay(id, tipoPagamento))
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Carrinho> cancel(@PathVariable Long id){
        service.cancel(id);
        return ResponseEntity.ok().build();
    }




}
