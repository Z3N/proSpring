package pro.spring.prospring.ch8.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ALBUM")
@Getter
@Setter
@ToString
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Column
    private String title;

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "SINGER_ID")
    private Singer singer;

    public Album() {

    }

    public Album(String title, Date releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }
}
