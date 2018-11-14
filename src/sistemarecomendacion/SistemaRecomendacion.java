/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import java.util.ArrayList;
import java.util.HashMap;
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
        
        /*
        
        System.out.println("Vote del 1 al 5 las siguientes peliculas:\n");
        
        for(int i=0;i < 10 ; i++){
            
            int movie_id = movies.getRandomMovie();
        
            System.out.println("    - " + movies.getMovie(movie_id) + " :");

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

            }
            
            rating_usuario.put(movie_id,Float.valueOf(entrada));
        
        }
        
        users.addUser(rating_usuario);
        */
        
        float sim = users.calcSim(1, 8);
        
        System.out.println(sim);
        
        ArrayList<Integer> aux = users.getKusuariosSimilaresA(1, 5);
        
        for(int i=0; i < aux.size(); i++){
            System.out.println(aux.get(i));
        }
        
    }
    
}
