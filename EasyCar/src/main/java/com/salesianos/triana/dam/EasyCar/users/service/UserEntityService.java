package com.salesianos.triana.dam.EasyCar.users.service;

import com.salesianos.triana.dam.EasyCar.errores.exception.ListEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.errores.exception.SingleEntityNotFoundException;
import com.salesianos.triana.dam.EasyCar.service.ConcesionarioService;
import com.salesianos.triana.dam.EasyCar.service.StorageService;
import com.salesianos.triana.dam.EasyCar.users.dto.Admin.CreateAdminDto;
import com.salesianos.triana.dam.EasyCar.users.dto.Gestor.CreateGestorDto;
import com.salesianos.triana.dam.EasyCar.users.dto.GetUserDto;
import com.salesianos.triana.dam.EasyCar.users.dto.GetUserFavDto;
import com.salesianos.triana.dam.EasyCar.users.dto.UserDtoConverter;
import com.salesianos.triana.dam.EasyCar.users.dto.Usuario.CreateUsuarioDto;
import com.salesianos.triana.dam.EasyCar.users.model.UserRole;
import com.salesianos.triana.dam.EasyCar.users.model.Usuario;
import com.salesianos.triana.dam.EasyCar.users.repo.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Optional;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final StorageService storageService;
    private final UserEntityRepository repository;
    private final UserDtoConverter converter;

    private final ConcesionarioService concesionarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findFirstByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " no encontrado."));
    }

    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Usuario.class));
    }

    public Page<GetUserDto> findAll(Pageable pageable) {
        Page<Usuario> data = repository.findAll(pageable);

        if(data.isEmpty()) {
            throw new ListEntityNotFoundException(Usuario.class);
        } else {
            return data.map(converter::convertUsuarioToNewUser);
        }
    }

    public Optional<Usuario> loadUserById (Long id) throws UsernameNotFoundException {
        return repository.findById(id);
    }

    public Usuario findUserById(Long id){
        return repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Usuario.class));
    }


    public GetUserDto getAuthUser(Usuario usuario) {
        Usuario data = repository.findById(usuario.getId()).orElseThrow(() -> new SingleEntityNotFoundException(usuario.getId().toString(), Usuario.class));
        return converter.convertUsuarioToNewUser(data);
    }
    public Usuario createUser (@Valid CreateUsuarioDto newUser, MultipartFile file) throws IOException{

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        if(newUser.getPassword().contentEquals(newUser.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .username(newUser.getUsername())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(uri)
                    .apellidos(newUser.getApellidos())
                    .telefono(newUser.getTelefono())
                    .nombre(newUser.getNombre())
                    .email(newUser.getEmail())
                    .rol(UserRole.USUARIO)
                    .build();
            return repository.save(usuario);
        }else {
            return null;
        }
    }

    public Usuario createGestor (@Valid CreateGestorDto newUser, MultipartFile file) throws IOException{

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        if(newUser.getPassword().contentEquals(newUser.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .username(newUser.getUsername())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(uri)
                    .apellidos(newUser.getApellidos())
                    .telefono(newUser.getTelefono())
                    .nombre(newUser.getNombre())
                    .email(newUser.getEmail())
                    .rol(UserRole.GESTOR)
                    .build();
            return repository.save(usuario);
        }else {
            return null;
        }
    }

    public Usuario createAdmin (@Valid CreateAdminDto newUser, MultipartFile file) throws IOException{

        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        if(newUser.getPassword().contentEquals(newUser.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .username(newUser.getUsername())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(uri)
                    .apellidos(newUser.getApellidos())
                    .telefono(newUser.getTelefono())
                    .nombre(newUser.getNombre())
                    .email(newUser.getEmail())
                    .rol(UserRole.ADMIN)
                    .build();
            return repository.save(usuario);
        }else {
            return null;
        }
    }

    public Usuario edit(CreateUsuarioDto createUsuarioDto, MultipartFile file, Usuario usuario) throws IOException {
        String filename = storageService.store(file);

        String extension = StringUtils.getFilenameExtension(filename);

        BufferedImage img = ImageIO.read(file.getInputStream());

        BufferedImage imgScale = storageService.resizer(img, 128);

        OutputStream out = Files.newOutputStream(storageService.load(filename));

        ImageIO.write(imgScale, extension, out);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(filename)
                .toUriString();

        return repository.findById(usuario.getId()).map(c -> {
            c.setNombre(createUsuarioDto.getNombre());
            c.setEmail(createUsuarioDto.getEmail());
            c.setUsername(createUsuarioDto.getUsername());
            c.setPassword(createUsuarioDto.getPassword());
            c.setPassword2(createUsuarioDto.getPassword2());
            c.setAvatar(uri);
            return repository.save(c);

        }).orElseThrow(() -> new SingleEntityNotFoundException(usuario.getId().toString(), Usuario.class));

    }

    public ResponseEntity<?> delete (Long id) throws IOException{

        Usuario usuario = repository.findById(id).orElseThrow(() -> new SingleEntityNotFoundException(id.toString(), Usuario.class));
        storageService.deleteFile(usuario.getAvatar());
        concesionarioService.delete(usuario.getConcesionario().getId());
        repository.delete(usuario);
        return ResponseEntity.noContent().build();

    }
    public GetUserFavDto getUserFavs(Usuario usuario) {
        Usuario data = repository.findById(usuario.getId()).orElseThrow(() -> new SingleEntityNotFoundException(usuario.getId().toString(), Usuario.class));
        return converter.convertUsuarioToNewUserFav(data);
    }

    public boolean comprobarUsername(String username) {
        return repository.existsByUsername(username);
    }

    public boolean comprobarEmail(String email){ return repository.existsByEmail(email);}
}
