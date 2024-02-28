package pro.spring.prospring.ch8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import pro.spring.prospring.ch8.config.JpaConfig;
import pro.spring.prospring.ch8.entity.Album;
import pro.spring.prospring.ch8.entity.Singer;
import pro.spring.prospring.ch8.service.SingerService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class SingerJPATest {
    private GenericApplicationContext ctx;
    private SingerService singerService;

    @BeforeEach
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean("jpaSingerService", SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindAllWithAlbum() {
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(3, singers.size());
        listSingersWithAlbum(singers);
    }

    private void listSingersWithAlbum(List<Singer> singers) {
        log.info("---- List singers: ----");
        for (Singer singer : singers) {
            log.info(singer.toString());
            if (singer.getAlbums() != null) {
                for (var album : singer.getAlbums()) {
                    log.info(album.toString());
                }
            }
            if (singer.getInstruments() != null) {
                for (var instrument : singer.getInstruments()) {
                    log.info(instrument.getInstrumentId());
                }
            }
        }
    }

    private static void listSingers(List<Singer> singers) {
        log.info("---- List singers with album: ----");
        singers.forEach(singer -> log.info(singer.toString()));
    }
    @Test
    public void testInsert() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date( new GregorianCalendar(1940, Calendar.SEPTEMBER, 16).getTime().getTime()));
        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new java.sql.Date(new GregorianCalendar(1962, Calendar.APRIL,20).getTime().getTime()));
        singer.addAlbum(album);

        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new java.sql.Date(new GregorianCalendar(1962, Calendar.APRIL, 20).getTime().getTime()));
        singer.addAlbum(album);
        singerService.save(singer);
        assertNotNull(singer.getId());
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(4,singers.size());
    }

    @Test
    public void testUpdate() {
        Singer singer = singerService.findById(1L);
        Album album = singer.getAlbums().stream()
                .filter(a -> a.getTitle().equals("Battle Studies"))
                .findFirst().get();
        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);
        singerService.save(singer);

        listSingersWithAlbum(singerService.findAllWithAlbum());
    }
    @Test
    public void testDelete() {
        Singer singer = singerService.findById(2L);
        assertNotNull(singer);
        singerService.delete(singer);
        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    @AfterEach
    public void tearDown() {
        ctx.close();
    }
}
