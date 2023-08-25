package formulairesClient.services.Old;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericServiceOld<Entity, ID, Repository extends JpaRepository<Entity, ID>> {
    protected final Repository repository;


    protected GenericServiceOld(Repository repository) {
        this.repository = repository;
    }

    public List<Entity> findAll() {
        return repository.findAll();

    }

//    public Optional<Entity> findById(ID id){
//        return repository.findById(id);
//    }

    public void deleteById(ID id){
        repository.deleteById(id);

    }

    public Entity saveOrUpdate(Entity entity) {
        return repository.save(entity);
    }
}
