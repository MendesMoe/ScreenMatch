package br.com.screenmatch.controller;

import br.com.screenmatch.model.movie.DataNewMovie;
import br.com.screenmatch.model.movie.DataUpdateMovie;
import br.com.screenmatch.model.movie.Movie;
import br.com.screenmatch.model.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired //nao precisa criar uma instancia com um new, é o spring que faz isso com essa anotation
    private MovieRepository repository;
    @GetMapping("/form")
    public String getPageForm(Long id, Model model) {
        if (id != null) {
            var movie = repository.getReferenceById(id);
            model.addAttribute("movie", movie);
        }
        return "movies/formMovie";
    }// nao pode ter duas urls com mesmo metodo, precisa especificar na anotation

    @GetMapping()
    public String getPageList(Model model) {
        model.addAttribute("listMovies", repository.findAll()); //necessario para enviar variaveis para a view
        return "movies/listMovies";
    }
    @PostMapping
    @Transactional
    public String saveNewMovie(DataNewMovie data) {
        var movie = new Movie(data);
        repository.save(movie);

        return "redirect:/movies"; // ao inves de repetir as linhas 26 e 27, rediretiona
    }

    @PutMapping
    @Transactional
    public String updateNewMovie(DataUpdateMovie data) {
        var movie = repository.getReferenceById(data.id());
        movie.updateData(data);

        return "redirect:/movies";
    }

    @DeleteMapping
    @Transactional
    public String deleteMovie(Long id) {
        repository.deleteById(id);
        return "redirect:/movies";
    }
}
