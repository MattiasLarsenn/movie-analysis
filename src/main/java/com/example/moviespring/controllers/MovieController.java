package com.example.moviespring.controllers;

import com.example.moviespring.models.Movie;
import com.example.moviespring.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
}
