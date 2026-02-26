package com.example.moviespring.services;

import com.example.moviespring.models.Movie;
import com.example.moviespring.repositories.MovieRepository;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieService {
    private MovieRepository movieRepo = new MovieRepository();
    private List<Movie> allMovies = movieRepo.getAllMovies();

    public Movie getFirst(){
        return allMovies.get(0);
    }

    public int getCount(){
        return allMovies.size();
    }

    public double averageLength()
    {
        int count = 0;
        for (int i = 0; i < allMovies.size(); i++)
        {
            Movie m = allMovies.get(i);
            count += m.getLength();
        }
        return (double) count/allMovies.size();
    }

    public double averageAwardsAge()
    {
        LocalDate today = LocalDate.now();
        int totalAge = 0;
        int sizeOfMoviesWithAwards = 0;
        for (int i = 0; i < allMovies.size(); i++)
        {
            Movie m = allMovies.get(i);
            if (m.isAwards())
            {
                totalAge += today.getYear() - m.getYear();
                sizeOfMoviesWithAwards++;
            }
        }

        if (sizeOfMoviesWithAwards == 0)
            return 0;
        return (double) totalAge/sizeOfMoviesWithAwards;
    }

    public String mostPopular()
    {
        Map<String, Integer> popularityMap = new HashMap<>();

        for (int i = 0; i < allMovies.size(); i++)
        {
            Movie m = allMovies.get(i);

            if (popularityMap.containsKey(m.getSubject()))
            {
                int count = popularityMap.get(m.getSubject()) + 1;
                popularityMap.put(m.getSubject(), count);
            }
            else
            {
                popularityMap.put(m.getSubject(), 1);
            }
        }
        int max = 0;
        String mostPopular = null;
        for (Map.Entry<String, Integer> entry : popularityMap.entrySet())
        {
            if (entry.getValue() > max)
            {
                max = entry.getValue();
                mostPopular = entry.getKey();
            }
        }
        return mostPopular;
    }

    public Map<String, Integer> howManyPerGenre()
    {
        Map<String, Integer> moviesPerGenre = new HashMap<String, Integer>();

        for (int i = 0; i < allMovies.size(); i++)
        {
            Movie m = allMovies.get(i);
            if (moviesPerGenre.containsKey(m.getSubject()))
            {
                int count = moviesPerGenre.get(m.getSubject()) + 1;
                moviesPerGenre.put(m.getSubject(), count);
            }
            else
            {
                moviesPerGenre.put(m.getSubject(), 1);
            }
        }
        return moviesPerGenre;
    }

    public int howManyPerYear(int year)
    {
        int count = 0;
        for (int i = 0; i < allMovies.size(); i++)
        {
            Movie m = allMovies.get(i);
            if (year == m.getYear())
            {
                count++;
            }
        }
        return count;
    }

    public List<String> howManyAboveAverage(String genre)
    {
       List<String> moviesAboveAverage = new ArrayList<>();
       int popularityInGenre = 0;
       int amountOfMoviesWithGenre = 0;
       for (int i = 0; i < allMovies.size(); i++)
       {
           Movie m = allMovies.get(i);
           if (m.getSubject().equalsIgnoreCase(genre))
           {
               popularityInGenre += m.getPopularity();
               amountOfMoviesWithGenre++;
           }
       }
       if (amountOfMoviesWithGenre == 0)
       {
           return moviesAboveAverage;
       }
       double average = (double) popularityInGenre / amountOfMoviesWithGenre;
       for (int i = 0; i < allMovies.size(); i++)
       {
           Movie m = allMovies.get(i);
           if (m.getSubject().equalsIgnoreCase(genre) && m.getPopularity() > average)
           {
               moviesAboveAverage.add(m.getTitle());
           }
       }
       return moviesAboveAverage;
    }
}
