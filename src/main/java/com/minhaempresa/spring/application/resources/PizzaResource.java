package com.minhaempresa.spring.application.resources;

import com.minhaempresa.spring.infrastructure.models.services.PizzaService;
import com.minhaempresa.spring.infrastructure.models.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaResource {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<List<Pizza>> findAll(){
        List<Pizza> pizzas = pizzaService.findAll();
        return ResponseEntity.ok().body(pizzas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pizza> findById(@PathVariable Long id){
        Pizza pizza = pizzaService.findById(id);
        return ResponseEntity.ok().body(pizza);
    }

    @PostMapping
    public ResponseEntity<Pizza> insert(@RequestBody Pizza pizza){
        pizza = pizzaService.pizzaRegistration(pizza);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(pizza.getId())
                .toUri();
        return ResponseEntity.created(uri).body(pizza);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pizzaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Long id, @RequestBody Pizza pizza){
        pizza = pizzaService.update(id, pizza);
        return ResponseEntity.ok().body(pizza);
    }
}
