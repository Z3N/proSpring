package pro.spring.prospring.ch6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import pro.spring.prospring.ch6.config.DbConfig;
import pro.spring.prospring.ch6.dao.SingerDao;
import pro.spring.prospring.ch6.entities.Album;
import pro.spring.prospring.ch6.entities.Singer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotationJdbcTest {
    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @BeforeEach
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(DbConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " + album);
                }
            }
        });
        ctx.close();
    }
    @AfterEach
    public void tearDown() {
        ctx.close();
    }
}
