package dev.carlosmz96.cvgen.cvgen_api.services;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.security.UserDTO;

public interface UserService {

    public boolean existePorEmail(String email);
    public UserDTO obtenerPorEmail(String email);
    public void registrarUsuario(UserDTO userDTO);

}
