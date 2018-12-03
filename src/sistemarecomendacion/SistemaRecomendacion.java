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
        
            /*System.out.println("    - " + movies.getMovie(movie_id) + " :");

            entrada = in.nextLine();
            introducido = false;

            while(!introducido){

                try{
                    Integer.valueOf(entrada);
                    if(Integer.valueOf(entrada)< 1 || Integer.valueOf(entrada) > 5){
                        System.out.println("El numero de votación debe ser siempre entre 1 y 5, intentelo de nuevo: ");
                        entrada = in.nextLine();
                    }else{
                        introducido=true;
                    }
                }catch(NumberFormatException e){
                    System.out.println("No ha introducido un número, intentelo de nuevo: ");
                    entrada = in.nextLine();
                }

            }*/
            Random rand = new Random();
            int id = abs(rand.nextInt() % 5);
            rating_usuario.put(movie_id,Float.valueOf(id+1));
        
        }
        
        users.addUser(900,rating_usuario);
        users.addUser(901,rating_usuario);

        
        users.calcMeanRatings();
        
       /* for(int i = 1; i < 10; i++){
           System.out.println("Media del usuario " + i + " = " + users.getMeanRating(i));           
        }*/
                
        
        
        
        Random rand = new Random();
        /*for(int i = 1; i < 10; i++){
            
            int id = abs(rand.nextInt() % users.size());
            float sim = users.calcSim(900,id);
            
            System.out.println("Similitud usuario activo con usuario num " + (id) + ": " + sim);
        }*/
        
        //System.out.println(sim);
        
        ArrayList<Integer> aux = users.getKusuariosSimilaresA(900, 10);
        
        System.out.println("Vecinos mas cercanos");
        for(int i=0; i < aux.size(); i++){
            System.out.println("usuario " + aux.get(i)+ " similitud = " + users.getSim(aux.get(i)));
            
        }
        
        ArrayList<Integer> aux2 = users.peliculasVecindario(900, aux);
        
        System.out.println("Peliculas no vistas por el usuario activo");
        for(int i=0; i < aux2.size(); i++){
            System.out.println(movies.getMovie(aux2.get(i)));
            
        }
        
        }
        
    
}
