package pro.spring.prospring.ch8.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.spring.prospring.ch8.entity.Singer;

import java.util.List;
@Slf4j
@Repository
@Transactional
@Service("jpaSingerService")
public class SingerServiceImpl implements SingerService {
    final static String ALL_SINGER_NATIVE_QUERY = "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION FROM SINGER";

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return entityManager
                .createNamedQuery(Singer.FIND_ALL, Singer.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAllWithAlbum() {
        throw new NotImplementedException("findAllWithAlbum() not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public Singer findById(Long id) {
        throw new NotImplementedException("findById() not implemented");
    }

    @Override
    public Singer save(Singer singer) {
        throw new NotImplementedException("save() not implemented");
    }

    @Override
    public Singer delete(Singer singer) {
        throw new NotImplementedException("delete() not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAllByNativeQuery() {
        throw new NotImplementedException("findAllByNativeQuery() not implemented");
    }
}
