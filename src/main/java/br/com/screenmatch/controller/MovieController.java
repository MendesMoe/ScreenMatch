package br.com.screenmatch.controller;

import br.com.screenmatch.model.movie.DataNewMovie;
import br.com.screenmatch.model.movie.Movie;
import br.com.screenmatch.model.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired //nao precisa criar uma instancia com um new, é o spring que faz isso com essa anotation
    private MovieRepository repository;
    @GetMapping("/form")
    public String getPageForm() {
        return "movies/formMovie";
    }// nao pode ter duas urls com mesmo metodo, precisa especificar na anotation

    @GetMapping()
    public String getPageList(Model model) {
        model.addAttribute("listMovies", repository.findAll()); //necessario para enviar variaveis para a view
        return "movies/listMovies";
    }
    @PostMapping
    public String saveNewMovie(DataNewMovie data) {
        var movie = new Movie(data);
        repository.save(movie);

        return "redirect:/movies"; // ao inves de repetir as linhas 26 e 27, rediretiona
    }
}
