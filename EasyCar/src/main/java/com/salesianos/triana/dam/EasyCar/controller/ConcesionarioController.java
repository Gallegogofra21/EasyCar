package com.salesianos.triana.dam.EasyCar.controller;

import com.salesianos.triana.dam.EasyCar.dto.concesionario.CreateConcesionarioDto;
import com.salesianos.triana.dam.EasyCar.dto.concesionario.GetConcesionarioDto;
import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.service.ConcesionarioService;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import com.salesianos.triana.dam.EasyCar.util.PaginationLinksUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/concesionario")
@RequiredArgsConstructor
@Validated
public class ConcesionarioController {

    private final ConcesionarioService service;
    private final PaginationLinksUtil paginationLinksUtil;

    @GetMapping("/")
    public ResponseEntity<?> findAll(Pageable pageable, HttpServletRequest request) {
        Page<GetConcesionarioDto> result = service.findAll(pageable);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @GetMapping("/{id}")
    public Concesionario findOne(@PathVariable Long id) { return service.findById(id); }

    @PostMapping("/{id}")
    public ResponseEntity<?> create (@Valid @RequestParam CreateConcesionarioDto newConcesionario, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createConcesionario(newConcesionario, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit (@PathVariable Long id, @Valid @RequestParam CreateConcesionarioDto newConcesionario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.edit(newConcesionario, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }
}
