package modelo;

import java.util.List;

/**
 *
 * @author arthur
 */
public interface AlimentoDAO {
    void create(Alimento material);
    void delete(Alimento material);
    void update(Alimento material);
    List<Alimento> all();
    Alimento findById(int id);
}

