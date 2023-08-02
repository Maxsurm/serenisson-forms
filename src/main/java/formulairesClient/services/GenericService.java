package formulairesClient.services;

import formulairesClient.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericService<Entity, ID, Repository extends JpaRepository<Entity, ID>> {
    protected final Repository repository;

    protected GenericService(Repository repository) {
        this.repository = repository;
    }

    public List<Entity> findAll() {
        return repository.findAll();

    }

    public void saveAndFlush(Patient patient) {
    }
}
