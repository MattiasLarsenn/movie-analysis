package com.example.moviespring.controllers;

import com.example.moviespring.models.Movie;
import com.example.moviespring.services.MovieService;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class MovieController {
    private MovieService service = new MovieService();

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("name","Nicklas");
        return mav;
    }

    //Return how many movies are in the data file
    @GetMapping("/count")
    public ModelAndView count(){
        ModelAndView mav = new ModelAndView("examples/count");
        int count = service.getCount();
        mav.addObject("count", count);
        return mav;
    }

    @GetMapping("/first")
    public ModelAndView getFirst(){
        ModelAndView mav = new ModelAndView();
        Movie first = service.getFirst();
        mav.addObject("first",first);
        return mav;
    }

    @GetMapping("/averageLength")
    public ModelAndView averageLength()
    {
        ModelAndView mav = new ModelAndView();
        double average = service.averageLength();
        mav.addObject("average", average);
        return mav;
    }

    @GetMapping("/averageAwardsAge")
    public ModelAndView averageAwardsAge()
    {
        ModelAndView mav = new ModelAndView();
        double averageAge = service.averageAwardsAge();
        mav.addObject("averageAge", averageAge);
        return mav;
    }

    @GetMapping("/mostPopular")
    public ModelAndView mostPopular()
    {
        ModelAndView mav = new ModelAndView();
        String mostPopular = service.mostPopular();
        mav.addObject("mostPopular", mostPopular);
        return mav;
    }

    @GetMapping("/howManyPerGenre")
    public ModelAndView howManyPerGenre()
    {
        ModelAndView mav = new ModelAndView();
        Map<String, Integer> moviesPerGenre = service.howManyPerGenre();
        mav.addObject("moviesPerGenre", moviesPerGenre);
        return mav;
    }

    @GetMapping("/howManyPerYear")
    public ModelAndView howManyPearYear(@RequestParam int year)
    {
        ModelAndView mav = new ModelAndView();
        int moviesPerYear = service.howManyPerYear(year);
        if (moviesPerYear == 0)
        {
            mav.setViewName("404page");
            mav.addObject("message", "There was no movies produced that year");
            mav.setStatus(HttpStatus.NOT_FOUND);
        }
        else {
            mav.setViewName("howManyPerYear");
            mav.addObject("year", year);
            mav.addObject("moviesPerYear", moviesPerYear);
        }
        return mav;
    }

    @GetMapping("/howManyAboveAverage")
    public ModelAndView howManyAboveAverage(@RequestParam String genre)
    {
        ModelAndView mav = new ModelAndView();
        List<String> moviesAboveAverage = service.howManyAboveAverage(genre);
        if (moviesAboveAverage.isEmpty())
        {
            mav.setViewName("404page");
            mav.addObject("message", "No movies in the genre '" + genre + "' are above average popularity");
            mav.setStatus(HttpStatus.NOT_FOUND);
        }
        else
        {
            mav.setViewName("howManyAboveAverage");
            mav.addObject("moviesAboveAverage", moviesAboveAverage);
            mav.addObject("genre", genre);
        }
        return mav;
    }
}
