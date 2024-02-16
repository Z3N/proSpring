package pro.spring.prospring.ch7.entities;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Setter
@Table(name = "SINGER")
public class Singer implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int version;
    private Set<Album> albums = new HashSet<>();
    private Set<Instrument> instruments = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "SINGER_INSTRUMENT", joinColumns = @JoinColumn(name = "SINGER_ID"), inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    public Set<Instrument> getInstruments() {
        return instruments;
    }

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Album> getAlbums() {
        return albums;
    }
    public boolean addAlbum(Album album) {
        album.setSinger(this);
        return getAlbums().add(album);
    }

    public void removeAlbum(Album album) {
        getAlbums().remove(album);
    }
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "FIRST_NAME")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

}
