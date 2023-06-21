package br.com.screenmatch.model.movie;

import jakarta.persistence.*;

@Entity //importante para fazer o mapeamento da entidade
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer duration;
    private Integer year;
    private String genre;

    public Movie(DataNewMovie data) {
        this.name = data.name();
        this.duration = data.duration();
        this.year = data.year();
        this.genre = data.genre();
    }

    public Movie(){}

    public String getName() {
        return name;
    }
    public Integer getDuration() {
        return duration;
    }
    public Integer getYear() {
        return year;
    }
    public String getGenre() {
        return genre;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                '}';
    }

    public void updateData(DataUpdateMovie data) {
        this.name = data.name();
        this.duration = data.duration();
        this.year = data.year();
        this.genre = data.genre();
    }
}
