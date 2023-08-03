package formulairesClient.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<Entity, ID, Repository extends JpaRepository<Entity, ID>> {
    protected final Repository repository;


    protected GenericService(Repository repository) {
        this.repository = repository;
    }

    public List<Entity> findAll() {
        return repository.findAll();

    }

    public Optional<Entity> findById(ID id){
        return repository.findById(id);
    }

    public void deleteById(ID id){
        repository.deleteById(id);

    }

    public Entity saveOrUpdate(Entity entity) {
        return repository.save(entity);
    }
}
