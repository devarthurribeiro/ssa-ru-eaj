package modelo;

import java.util.List;

/**
 *
 * @author arthur
 */
public interface MaterialDAO {
    void create(Material material);
    void delete(Material material);
    void update(Material material);
    List<Material> all();
    Material findById(int id);
}

