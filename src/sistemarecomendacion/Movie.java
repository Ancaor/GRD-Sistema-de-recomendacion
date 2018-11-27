/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author ancaor
 */
public class Movie {
    
    private HashMap<Integer,String> movies;
    
    public Movie(String movieDB){
        this.movies = loadMovies(movieDB);
    }

    public HashMap<Integer,String> loadMovies(String file){
        HashMap<Integer,String> movies = new HashMap<>();
        
        try {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String linea = br.readLine();
        linea = br.readLine(); // salta la primera linea
        while(linea != null){
            
          String[] valores_linea = linea.split(",");
          //String a = valores_linea[0];
        //  int aux = Integer.valueOf(valores_linea[0])
      //    System.out.println();
          movies.put(Integer.valueOf(valores_linea[0]), valores_linea[1]);
          
          linea = br.readLine();
        }

        fr.close();
        }
        catch(Exception e) {
            System.out.println("Excepcion leyendo fichero "+ file + ": " + e);
        }
        
        return movies;
    }
    
    public String getMovie(int id){
        return this.movies.get(id);
    }

    public int getRandomMovie(){
        Random rand = new Random();
        int id = rand.nextInt() % movies.size();
        while(!movies.containsKey(id))
            id = rand.nextInt() % movies.size();
        return id; 
    }
   public int size(){
       return movies.size();
   }
}
