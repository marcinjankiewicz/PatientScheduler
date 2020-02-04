package pl.jankiewicz.patientscheduler.PatientScheduler.backend.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract.AbstractEntity;

import javax.persistence.EntityNotFoundException;

public interface CrudService<T extends AbstractEntity> {
    JpaRepository<T, Long> getRepository();

    default T save(T entity) {
        return getRepository().saveAndFlush(entity);
    }

    default T update(T entity) {
        return getRepository().saveAndFlush(entity);
    }

    default void delete(T entity) {
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        getRepository().delete(entity);
    }

    default long count() {
        return getRepository().count();
    }

    default T load(long id) {
        T entity = getRepository().findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return entity;
    }

    T createNew();
}
