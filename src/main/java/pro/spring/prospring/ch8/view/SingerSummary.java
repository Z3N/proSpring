package pro.spring.prospring.ch8.view;

import lombok.Data;

import java.io.Serializable;
@Data
public class SingerSummary implements Serializable {
    private String firstName;
    private String lastName;
    private String latestAlbum;
}
