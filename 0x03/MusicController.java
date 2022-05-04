package com.ciandt.summit.bootcamp2022.adapter.rest;

import com.ciandt.summit.bootcamp2022.entity.Musicas;
import com.ciandt.summit.bootcamp2022.usecase.MusicasService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/musicas")
public class MusicController {

    private static final Logger logger = LoggerFactory.getLogger(MusicController.class);

    private Map<String, Object> response = new HashMap<>();

    @Autowired
    private MusicasService musicasService;

        @GetMapping
        public ResponseEntity<?> encontrarMusicas (@RequestParam ("filtro") String nome) {

            try{

                if(nome.length() < 3){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                List<Musicas> listaMusicas = musicasService.buscarMusicas(nome);

                if(listaMusicas.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }else{
                    response.clear();
                    response.put("musicas", listaMusicas);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }catch (Exception e){
                throw new RuntimeException("Erro ao realizar a busca");
            }
        }

}