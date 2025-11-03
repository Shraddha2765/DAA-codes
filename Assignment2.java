import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Movie{
    String title;
    int year;
    double movieRating;
    long population;

    Movie(String t , int y , double mR, long p){
        title = t;
        year = y;
        movieRating = mR;
        population = p;
    }
}
public class Assignment2{
    private static boolean compareByYear(Movie a, Movie b){
        return a.year > b.year;
    }
    
    private static int partition(List<Movie> movies, int low , int high){
        Movie  pivot = movies.get(high);
            int i = low - 1;
        if(low<high){
            
            for(int j = low;j<movies.size();j++){
                if(compareByYear(movies.get(j), pivot)){
                    i++;
                    Collections.swap(movies, i, j);
                }
            }
            Collections.swap(movies, i+1, high);
        }
        return i+1;
    }

    private static void quickSort(List<Movie> movies ,int low , int high){
            if(low<high){
                int pi = partition(movies, low, high);
                quickSort(movies, low, pi - 1);
                quickSort(movies, pi+1, high);
            }
    }

    private static List<Movie> readMoviesFromCSV(String filepath){
        List<Movie> movies = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line;
            br.readLine();

            while((line = br.readLine() ) != null){
                String[] values = line.split(",");
                if(values.length == 4){
                    String name = values[0].trim();
                    int year = Integer.parseInt(values[1].trim());
                    double rating = Double.parseDouble(values[2].trim());
                    long population = Long.parseLong(values[3].trim());
                    movies.add(new Movie(name,year,rating,population));
                } 
            }
        }

        catch (IOException e){
            System.out.println("Error loading csv file ");
            e.printStackTrace();
        }

        return movies;
    }

public static void main(String[] args) {
        List<Movie> movies = new ArrayList<>();
        String filepath = "movies.csv";
        movies = readMoviesFromCSV(filepath);
        if(movies.isEmpty()){
            System.out.println("no movie data found");
            return;
        }
        quickSort(movies, 0, movies.size() - 1);
        System.out.println("Movies sorted by Year (Latest to Old):");
        for (Movie m : movies) {
            System.out.println(m.title + " (" + m.year + ")");
        }
    }
}