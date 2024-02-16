package pro.spring.prospring.ch7.dao;

import jakarta.annotation.Resource;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.spring.prospring.ch7.entities.Singer;

import java.util.List;

@Transactional
@Repository("singerDaoCh7")
public class SingerDaoImpl implements pro.spring.prospring.ch7.dao.SingerDao {
    @Getter
    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Singer s", Singer.class).list();
    }

    @Override
    public List<Singer> findAllWithAlbum() {
        return null;
    }

    @Override
    public Singer findById(Long id) {
        return null;
    }

    @Override
    public Singer save(Singer contact) {
        return null;
    }

    @Override
    public Singer delete(Singer contact) {
        return null;
    }
}
