package com.salesianos.triana.dam.EasyCar.controller;

import com.salesianos.triana.dam.EasyCar.dto.tipo.CreateTipoDto;
import com.salesianos.triana.dam.EasyCar.dto.tipo.GetTipoDto;
import com.salesianos.triana.dam.EasyCar.dto.tipo.GetTipoVehiculosDto;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import com.salesianos.triana.dam.EasyCar.service.TipoService;
import com.salesianos.triana.dam.EasyCar.util.PaginationLinksUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/tipo")
@RequiredArgsConstructor
@Validated
public class TipoController {

    private final TipoService service;
    private final PaginationLinksUtil paginationLinksUtil;

    @GetMapping("/")
    public ResponseEntity<?> findAll (Pageable pageable, HttpServletRequest request) {
        Page<GetTipoDto> result = service.findAll(pageable);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        return ResponseEntity.ok().header("link", paginationLinksUtil.createLinkHeader(result, uriBuilder)).body(result);
    }

    @GetMapping("/{id}")
    public GetTipoVehiculosDto findOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> create (@RequestPart("file")MultipartFile file, @Valid @RequestPart("tipo")CreateTipoDto newTipo) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTipo(newTipo, file));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestPart("tipo") CreateTipoDto newTipo, @RequestPart("file") MultipartFile file) throws IOException{
        return ResponseEntity.status(HttpStatus.CREATED).body(service.edit(newTipo, file, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }
}
