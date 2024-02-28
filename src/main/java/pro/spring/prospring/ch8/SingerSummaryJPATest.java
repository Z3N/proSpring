package pro.spring.prospring.ch8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import pro.spring.prospring.ch8.config.JpaConfig;
import pro.spring.prospring.ch8.service.SingerSummaryUntypeImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class SingerSummaryJPATest {
    private GenericApplicationContext ctx;
    private SingerSummaryUntypeImpl singerSummaryUntype;

    @BeforeEach
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerSummaryUntype = ctx.getBean(SingerSummaryUntypeImpl.class);
        assertNotNull(singerSummaryUntype);
    }

    @Test
    public void testFindAllUntype() {
        singerSummaryUntype.displayAllSingersSummary();
    }

    @AfterEach
    public void tearDown() {
        ctx.close();
    }
}
