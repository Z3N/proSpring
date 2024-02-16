package pro.spring.prospring.ch7.entities;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@ToString
@Table(name = "INSTRUMENT")
public class Instrument implements Serializable {
    private String instrumentId;
    private Set<Singer> singers = new HashSet<>();

    @Id
    @Column(name = "INSTRUMENT_ID")
    public String getInstrumentId() {
        return instrumentId;
    }
    @ManyToMany
    @JoinTable(name = "SINGER_INSTRUMENT", joinColumns = @JoinColumn(name = "INSTRUMENT_ID"), inverseJoinColumns = @JoinColumn(name = "SINGER_ID"))
    public Set<Singer> getSingers() {
        return singers;
    }
}
