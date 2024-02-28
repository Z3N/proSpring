package pro.spring.prospring.ch8.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Repository
@Transactional
public class SingerSummaryUntypeImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public void displayAllSingersSummary() {
        List result = entityManager.createQuery(
                "select s.firstName, s.lastName, a.title from Singer s " +
                "left join s.albums a " +
                "where a.releaseDate=(select max(a2.releaseDate) from Album a2 where a2.singer.id=s.id) ")
        .getResultList();
                int count = 0;
        for (Object o : result) {
            Object[] row = (Object[]) o;
            System.out.println(++count + ": " + row[0] + ", " + row[1] + ", " + row[2]);
        }
    }
}
