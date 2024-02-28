package pro.spring.prospring.ch8.service;


import pro.spring.prospring.ch8.entity.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);
    List<Singer> findAllByNativeQuery();
}
