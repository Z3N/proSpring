package pro.spring.prospring.ch8.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
        List<Singer> singers = entityManager.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
        return singers;
    }

    @Override
    @Transactional(readOnly = true)
    public Singer findById(Long id) {
        TypedQuery<Singer> query = entityManager.createNamedQuery(Singer.FIND_SINGER_BY_ID, Singer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        if (singer.getId() == null) {
            log.info("Inserting new singer");
            entityManager.persist(singer);
        } else {
            entityManager.merge(singer);
            log.info("Updating existing singer");
        }
        log.info("Singer saved with id: " + singer.getId());
        return singer;
    }

    @Override
    public void delete(Singer singer) {
        Singer mergedSinger = entityManager.merge(singer);
        entityManager.remove(mergedSinger);
        log.info("Singer deleted with id: " + singer.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAllByNativeQuery() {
        throw new NotImplementedException("findAllByNativeQuery() not implemented");
    }
}
