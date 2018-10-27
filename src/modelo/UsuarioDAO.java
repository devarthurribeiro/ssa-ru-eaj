package modelo;

import java.util.List;

/**
 *
 * @author arthur
 */
public interface UsuarioDAO {
    void create(Usuario usuario);
    void delete(Usuario usuario);
    void update(Usuario usuario);
    List<Usuario> all();
    Usuario findById(int id);
}
