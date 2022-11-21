package com.heroku.tpe5.controller;

import com.heroku.tpe5.model.InscriptionAto;
import com.heroku.tpe5.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    /**
     * b) matricular un estudiante en una carrera
     *
     * @param inscriptionAto
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InscriptionAto createInscription(@RequestBody @Validated InscriptionAto inscriptionAto) {
        return inscriptionService.createInscription(inscriptionAto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InscriptionAto> getInscriptions() {
        return inscriptionService.getInscriptions();
    }
}
