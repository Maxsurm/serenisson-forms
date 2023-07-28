package formulairesClient.services;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericService<Entity, ID, Repository extends JpaRepository<Entity, ID>> {
    protected final Repository repository;

    protected GenericService(Repository repository) {
        this.repository = repository;
    }
}
