package plaxi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import plaxi.backend.dto.PerfilDto;
import plaxi.backend.entity.Persona;
import plaxi.backend.entity.Usuario;
import plaxi.backend.repository.PersonaRepository;
import plaxi.backend.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfilService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PersonaRepository personaRepository;

    public List<PerfilDto> getAllProfiles() {

        List<Usuario> usuarios = usuarioRepository.findAllByStatusTrue();

        return usuarios.stream().map(usuario -> {
            Persona persona = usuario.getPersonaId();
            return new PerfilDto(
                    usuario.getIdUsuario(),
                    usuario.getUsername(),
                    usuario.getGmail(),
                    usuario.isStatus(),
                    persona.getNombre(),
                    persona.getPrimerApellido(),
                    persona.getSegundoApellido(),
                    persona.getTelefono(),
                    persona.getCi()
            );
        }).collect(Collectors.toList());
    }


    // Obtener el perfil del usuario (incluyendo los datos de persona)
    public PerfilDto getProfile(Long idUsuario) throws Exception {
        // Buscar al usuario por ID
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        // Verificar si el status es false (borrado lógico)
        if (!usuario.isStatus()) {
            throw new Exception("El usuario ha sido desactivado");
        }

        // Obtener los datos de la persona asociada al usuario
        Persona persona = usuario.getPersonaId();

        // Retornar el DTO con la información del perfil
        return new PerfilDto(
            usuario.getIdUsuario(),
            usuario.getUsername(),
            usuario.getGmail(),
            usuario.isStatus(),
            persona.getNombre(),
            persona.getPrimerApellido(),
            persona.getSegundoApellido(),
            persona.getTelefono(),
            persona.getCi()
        );
    }


    // Actualizar el perfil del usuario (incluyendo datos de persona)
    public PerfilDto updateProfile(Long idUsuario, PerfilDto perfilDto) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        Persona persona = usuario.getPersonaId();

        // Actualizar datos del usuario
        usuario.setUsername(perfilDto.getUsername());
        usuario.setGmail(perfilDto.getGmail());
        // Omitir cambiar la contraseña aquí por razones de seguridad, debería hacerse en otro proceso
        usuarioRepository.save(usuario);

        // Actualizar datos de la persona
        persona.setIdPersona(perfilDto.getIdUsuario());
        persona.setNombre(perfilDto.getNombre());
        persona.setPrimerApellido(perfilDto.getPrimerApellido());
        persona.setSegundoApellido(perfilDto.getSegundoApellido());
        persona.setTelefono(perfilDto.getTelefono());
        persona.setCi(perfilDto.getCi());
        personaRepository.save(persona);

        return perfilDto;
    }

    // Borrado lógico del perfil (solo cambia el estado del usuario a falso)
    public void deleteProfile(Long idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        usuario.setStatus(false); // Borrado lógico
        usuarioRepository.save(usuario);
    }
}
