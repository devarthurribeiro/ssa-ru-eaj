package modelo;

import java.util.List;

/**
 *
 * @author arthur
 */
public interface SetorDAO {
    void create(Setor setor);
    void delete(Setor setor);
    void update(Setor setor);
    List<Setor> all();
    Setor findById(int id);
}
