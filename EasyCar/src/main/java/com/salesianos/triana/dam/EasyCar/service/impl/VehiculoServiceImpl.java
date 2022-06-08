package com.salesianos.triana.dam.EasyCar.service.impl;

import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDetails;
import com.salesianos.triana.dam.EasyCar.errores.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.errores.exception.SingleEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.model.Concesionario;
import com.salesianos.triana.dam.EasyCar.model.Marca;
import com.salesianos.triana.dam.EasyCar.model.Tipo;
import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import com.salesianos.triana.dam.EasyCar.repo.ConcesionarioRepository;
import com.salesianos.triana.dam.EasyCar.repo.MarcaRepository;
import com.salesianos.triana.dam.EasyCar.repo.TipoRepository;
import com.salesianos.triana.dam.EasyCar.repo.VehiculoRepository;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.ConverterVehiculoDto;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.CreateVehiculoDto;
import com.salesianos.triana.dam.EasyCar.dto.vehiculo.GetVehiculoDto;
import com.salesianos.triana.dam.EasyCar.service.StorageService;
import com.salesianos.triana.dam.EasyCar.service.VehiculoService;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import com.salesianos.triana.dam.EasyCar.users.repo.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository repository;
    private final StorageService storageService;
    private final ConverterVehiculoDto converter;
    private final ConcesionarioRepository concesionarioRepository;
    private final MarcaRepository marcaRepository;
    private final TipoRepository tipoRepository;

    private final UserEntityRepository userRepository;

    @Override
    public GetVehiculoDto createVehiculo(CreateVehiculoDto createVehiculoDto, MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, Long idConcesionario) throws IOException {

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(storageService.store(file1))
                .toUriString();
        String uri2 = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(storageService.store(file2))
                .toUriString();
        String uri3 = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(storageService.store(file3))
                .toUriString();
        String uri4 = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(storageService.store(file4))
                .toUriString();

        Concesionario concesionario = concesionarioRepository.findById(idConcesionario).orElseThrow(() -> new SingleEntityNotFoundException(idConcesionario.toString(), Concesionario.class));

        Marca marca = marcaRepository.findById(createVehiculoDto.getMarca()).orElseThrow(() -> new SingleEntityNotFoundException(createVehiculoDto.getMarca().toString(), Marca.class));

        Tipo tipo = tipoRepository.findById(createVehiculoDto.getTipo()).orElseThrow(() -> new SingleEntityNotFoundException(createVehiculoDto.getTipo().toString(), Tipo.class));

        Vehiculo newVehiculo = Vehiculo.builder()
                .version(createVehiculoDto.getVersion())
                .modelo(createVehiculoDto.getModelo())
                .fechaMatriculacion(createVehiculoDto.getFechaMatriculacion())
                .kilometraje(createVehiculoDto.getKilometraje())
                .potencia(createVehiculoDto.getPotencia())
                .marchas(createVehiculoDto.getMarchas())
                .precio(createVehiculoDto.getPrecio())
                .marca(marca)
                .tipo(tipo)
                .foto1(uri)
                .foto2(uri2)
                .foto3(uri3)
                .foto4(uri4)
                .llantas(createVehiculoDto.getLlantas())
                .distribucion(createVehiculoDto.getDistribucion())
                .procedencia(createVehiculoDto.getProcedencia())
                .traccion(createVehiculoDto.getTraccion())
                .concesionario(concesionario)
                .build();
        repository.save(newVehiculo);
        concesionario.getVehiculos().add(newVehiculo);
        tipo.getVehiculos().add(newVehiculo);
        marca.getVehiculos().add(newVehiculo);
        concesionarioRepository.save(concesionario);
        marcaRepository.save(marca);
        tipoRepository.save(tipo);

        repository.save(newVehiculo);

        return converter.getVehiculoToDto(newVehiculo);
    }

    @Override
    public Page<GetVehiculoDto> findAll(Pageable pageable,
                                        final Optional<String> marca,
                                        final Optional<String> modelo,
                                        final Optional<Float> precioMax,
                                        final Optional<Float> precioMin,
                                        final Optional<String> tipo) {
        String vacio="";

        Specification<Vehiculo> specMarcaVehiculo = new Specification<Vehiculo>() {
            @Override
            public Predicate toPredicate(Root<Vehiculo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (marca.isPresent()) {
                    return criteriaBuilder.like(criteriaBuilder.lower(root.get("marca")), "%" + marca.get().toLowerCase() + "%");
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vehiculo> specModeloVehiculo = new Specification<Vehiculo>() {
            @Override
            public Predicate toPredicate(Root<Vehiculo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (modelo.isPresent()) {
                    return criteriaBuilder.like(criteriaBuilder.lower(root.get("modelo")), "%" + modelo.get().toLowerCase() + "%");
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vehiculo> precioMenorQue = new Specification<Vehiculo>() {
            @Override
            public Predicate toPredicate(Root<Vehiculo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (precioMax.isPresent()) {
                    return criteriaBuilder.lessThanOrEqualTo(root.get("precio"), precioMax.get());
                } else {
                    return criteriaBuilder.isTrue((criteriaBuilder.literal(true)));
                }
            }
        };

        Specification<Vehiculo> precioMayorQue = new Specification<Vehiculo>() {
            @Override
            public Predicate toPredicate(Root<Vehiculo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (precioMin.isPresent()){
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("precio"), precioMin.get());
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }

            }
        };

        Specification<Vehiculo> specTipoVehiculo = new Specification<Vehiculo>() {
            @Override
            public Predicate toPredicate(Root<Vehiculo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (tipo.isPresent()) {
                    return criteriaBuilder.like(criteriaBuilder.lower(root.get("tipo")), "%" + tipo.get().toLowerCase() + "%");
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vehiculo> todos = specMarcaVehiculo.and(specModeloVehiculo).and(precioMayorQue).and(precioMenorQue).and(specTipoVehiculo);
        Page<Vehiculo> data = repository.findAll(todos, pageable);

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Vehiculo.class);
        } else {
            return data.map(converter::getVehiculoToDto);
        }
    }

    @Override
    public GetVehiculoDetails findById(Long id) {
        Vehiculo vehiculo = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Vehiculo.class));

        GetVehiculoDetails result = GetVehiculoDetails.builder()
                .id(vehiculo.getId())
                .version(vehiculo.getVersion())
                .modelo(vehiculo.getModelo())
                .fechaMatriculacion(vehiculo.getFechaMatriculacion())
                .kilometraje(vehiculo.getKilometraje())
                .potencia(vehiculo.getPotencia())
                .marchas(vehiculo.getMarchas())
                .precio(vehiculo.getPrecio())
                .nombreMarca(vehiculo.getMarca().getNombre())
                .tipo(vehiculo.getTipo().getId())
                .foto1(vehiculo.getFoto1())
                .llantas(vehiculo.getLlantas())
                .distribucion(vehiculo.getDistribucion())
                .procedencia(vehiculo.getProcedencia())
                .traccion(vehiculo.getTraccion())
                .concesionario(vehiculo.getConcesionario().getId())
                .build();

        return result;
    }

    @Override
    public List<GetVehiculoDto> findAllByConcesionario(Concesionario concesionario) {
        List<Vehiculo> data = repository.findAllByConcesionario(concesionario);

        return data.stream().map(converter::getVehiculoToDto).collect(Collectors.toList());
    }

    @Override
    public List<GetVehiculoDto> findAllByMarca(Marca marca) {
        List<Vehiculo> data = repository.findAllByMarca(marca);

        return data.stream().map(converter::getVehiculoToDto).collect(Collectors.toList());
    }

    @Override
    public List<GetVehiculoDto> findAllByTipo(Tipo tipo) {
        List<Vehiculo> data = repository.findAllByTipo(tipo);

        return data.stream().map(converter::getVehiculoToDto).collect(Collectors.toList());
    }

    @Override
    public GetVehiculoDto edit(CreateVehiculoDto createVehiculoDto, MultipartFile file, Usuario usuario, Long id) {

        Concesionario concesionario = concesionarioRepository.findById(createVehiculoDto.getConcesionario()).orElseThrow(() -> new SingleEntityNotFoundException(createVehiculoDto.getConcesionario().toString(), Concesionario.class));

        String filename = storageService.store(file);

        Marca marca = marcaRepository.findById(createVehiculoDto.getMarca()).orElseThrow(() -> new SingleEntityNotFoundException(createVehiculoDto.getMarca().toString(), Marca.class));

        Tipo tipo = tipoRepository.findById(createVehiculoDto.getTipo()).orElseThrow(() -> new SingleEntityNotFoundException(createVehiculoDto.getTipo().toString(), Tipo.class));

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        Vehiculo vehiculo = repository.findById(id).map(v -> {
            v.setVersion(createVehiculoDto.getVersion());
            v.setModelo(createVehiculoDto.getModelo());
            v.setFechaMatriculacion(createVehiculoDto.getFechaMatriculacion());
            v.setKilometraje(createVehiculoDto.getKilometraje());
            v.setPotencia(createVehiculoDto.getPotencia());
            v.setMarchas(createVehiculoDto.getMarchas());
            v.setPrecio(createVehiculoDto.getPrecio());
            v.setMarca(marca);
            v.setTipo(tipo);
            v.setFoto1(uri);
            v.setLlantas(createVehiculoDto.getLlantas());
            v.setDistribucion(createVehiculoDto.getDistribucion());
            v.setProcedencia(createVehiculoDto.getProcedencia());
            v.setTraccion(createVehiculoDto.getTraccion());
            v.setConcesionario(concesionario);
            return repository.save(v);
        }).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Vehiculo.class));

        return converter.getVehiculoToDto(vehiculo);
    }

    @Override
    public ResponseEntity<?> delete(Long id) throws IOException {

        Vehiculo vehiculo = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Vehiculo.class));
        storageService.deleteFile(vehiculo.getFoto1());
        vehiculo.setConcesionario(null);
        vehiculo.setMarca(null);
        vehiculo.setTipo(null);
        vehiculo.setUser(null);
        repository.save(vehiculo);
        repository.delete(vehiculo);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> addVehiculoToFav(Long id, Usuario user) {
        Vehiculo vehiculo = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Vehiculo.class));
        user.getVehiculosFav().add(vehiculo);
        userRepository.save(user);
        return ResponseEntity.ok(userRepository.save(user));
    }
}
