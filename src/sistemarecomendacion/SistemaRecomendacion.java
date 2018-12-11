/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ancaor
 */
public class SistemaRecomendacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Movie movies = new Movie("./ml-latest-small/movies.csv");
        System.out.println(movies.getMovie(1));
        Users users = new Users("./ml-latest-small/ratings.csv");
        System.out.println(users.getRating(1,70));
        
        String entrada;
        Scanner in = new Scanner(System.in);
        Boolean introducido = false;
        
        HashMap<Integer,Float> rating_usuario = new HashMap<>();
        
        
        
        System.out.println("Vote del 1 al 5 las siguientes peliculas:\n");
        int movie_id=0;
        for(int i=0;i < 10 ; i++){
            
            movie_id = movies.getRandomMovie();
        
            Random rand = new Random();
            int id = abs(rand.nextInt() % 5);
            rating_usuario.put(movie_id,Float.valueOf(id+1));
        
        }
        
        users.addUser(900,rating_usuario);
        users.addUser(901,rating_usuario);

        
        users.calcMeanRatings();
                
        
        
        
        Random rand = new Random();
        
        ArrayList<Integer> aux = users.getKusuariosSimilaresA(900, 10);
        
        System.out.println("Vecinos mas cercanos");
        for(int i=0; i < aux.size(); i++){
            System.out.println("usuario " + aux.get(i)+ " similitud = " + users.getSim(aux.get(i)));
            
        }
        
        ArrayList<Integer> aux2 = users.peliculasVecindario(900, aux);
        HashMap<Integer,Float> aux3 = users.prediccionNoVistas(900,aux, aux2);
        
        System.out.println("nPeliculas recomendadas al usuario activo\n");
        
        for (Map.Entry<Integer, Float> entry: aux3.entrySet()){
            System.out.println("id_pelicula: " + entry.getKey() + " valoración: " + entry.getValue());
        }
        
    
        
        }
        
    
}
