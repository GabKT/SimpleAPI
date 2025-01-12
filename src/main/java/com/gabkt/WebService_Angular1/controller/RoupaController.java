package com.gabkt.WebService_Angular1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabkt.WebService_Angular1.model.Roupa;
import com.gabkt.WebService_Angular1.service.RoupaService;

@RestController
@RequestMapping("/roupas")
public class RoupaController {
    @Autowired
    private RoupaService roupaService;

    @GetMapping("/{id}")
    public ResponseEntity<Roupa> listarRoupaPorId(@PathVariable int id) {
        Roupa roupa = roupaService.buscarRoupaPorId(id);
        if (roupa != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(roupa);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Roupa>> listarRoupas() {
        List<Roupa> roupas = roupaService.listarTodasRoupas();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roupas);
    }

    @PostMapping("/admin")
    public ResponseEntity<Roupa> inserirRoupa(@RequestBody Roupa roupa) {
        Roupa resposta = roupaService.inserirRoupa(roupa);
        if (resposta != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(resposta);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/admin")
    public ResponseEntity<Roupa> atualizarRoupa(@RequestBody Roupa roupa) {
        Roupa resRoupa = roupaService.atualizarRoupa(roupa);
        if (resRoupa != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(resRoupa);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin")
    public ResponseEntity<List<Integer>> deletarRoupas(@RequestBody List<Integer> listOfIds) {
        List<Integer> idsDeletados = List.of();
        if (listOfIds != null) {
            idsDeletados = roupaService.deletarRoupas(listOfIds);
        }
        return ResponseEntity.status(HttpStatus.OK).body(idsDeletados);
    }
}
