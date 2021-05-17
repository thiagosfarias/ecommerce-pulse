package com.thiago.ecommerce.services;

import com.thiago.ecommerce.entities.Cartao;
import com.thiago.ecommerce.repositories.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {
    @Autowired
    private CartaoRepository repository;

    public List<Cartao> findAll(){
        return Optional.ofNullable(repository.findAll()).get();
    }

    public Cartao findById(Long id){
        return Optional.ofNullable(repository.findById(id)).get().orElseThrow();
    }

    public Cartao novo(Cartao obj){
        Optional<Cartao> hasCartao = Optional.ofNullable(findAll())
                                            .get()
                                            .stream()
                                            .filter(cartao -> cartao.getnCartao().equals(obj.getnCartao()))
                                            .findFirst();

            if(hasCartao.isPresent()){
                return hasCartao.get();
            }

            return Optional.ofNullable(repository.save(obj)).get();
    }


    public Cartao update(Cartao obj){
        Optional<Cartao> oldCartao = Optional.ofNullable( findById(obj.getId()) );

        if(oldCartao.isPresent()){
            oldCartao.get().setnCartao(obj.getnCartao());
            oldCartao.get().setTitular(obj.getTitular());
            oldCartao.get().setValidade(obj.getValidade());
            oldCartao.get().setCpfTitular(obj.getCpfTitular());
            oldCartao.get().setCliente(obj.getCliente());
            oldCartao.get().setPagamentos(obj.getPagamentos());

            return repository.save(oldCartao.get());
        }

        return repository.save(obj);
    }

    public void delete(Long id){
        Cartao cartao = findById(id);
        if(cartao != null){
            repository.delete(cartao);
        }
    }
}
