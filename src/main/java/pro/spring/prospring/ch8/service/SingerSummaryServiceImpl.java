package pro.spring.prospring.ch8.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.spring.prospring.ch8.view.SingerSummary;

import java.util.List;
@Repository
@Transactional
@Service("singerSummaryService")
public class SingerSummaryServiceImpl implements SingerSummaryService {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional(readOnly = true)
    public List<SingerSummary> findAll() {
        return entityManager.createQuery(
        "select new pro.spring.prospring.ch8.view.SingerSummary(s.firstName, s.lastName, a.title) " +
        "from Singer s " +
        "left join s.albums a " +
        "where a.releaseDate=(select max(a2.releaseDate) from Album a2 where a2.singer.id=s.id) ", SingerSummary.class
        ).getResultList();
    }
}
