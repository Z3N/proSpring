package pro.spring.prospring.ch7.dao;

import pro.spring.prospring.ch7.entities.Singer;

import java.util.List;

public interface SingerDao {

    List<Singer> findAll();
    List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer contact);
    Singer delete(Singer contact);
}
