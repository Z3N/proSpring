package pro.spring.prospring.ch6.entities;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
@Data
@ToString
public class Singer implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<Album> albums;

    public boolean addAlbum(Album album) {
        if (albums == null) {
            albums = new java.util.ArrayList<>();
            albums.add(album);
            return true;
        } else {
            if (albums.contains(album)) {
                return false;
            }
        }
        albums.add(album);
        return true;
    }
}
