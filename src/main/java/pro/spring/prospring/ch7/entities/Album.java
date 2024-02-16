package pro.spring.prospring.ch7.entities;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Entity
@ToString
@Table(name = "ALBUM")
public class Album implements Serializable {
    private Long id;
    private String title;
    private Date releaseDate;
    private int version;
    private Singer singer;

    @ManyToOne
    @JoinColumn(name = "SINGER_ID")
    public Singer getSinger() {
        return singer;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }


    @Column
    public String getTitle() {
        return title;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE")
    public Date getReleaseDate() {
        return releaseDate;
    }


    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }
}
