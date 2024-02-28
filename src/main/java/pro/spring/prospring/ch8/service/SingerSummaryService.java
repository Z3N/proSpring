package pro.spring.prospring.ch8.service;

import pro.spring.prospring.ch8.view.SingerSummary;

import java.util.List;

public interface SingerSummaryService {
    List<SingerSummary> findAll();
}
