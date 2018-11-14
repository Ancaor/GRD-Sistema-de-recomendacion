/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.sqrt;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author ancaor
 */
public class Users {
    
    private HashMap<Integer,HashMap<Integer,Float>> users = new HashMap<>();  // id_usuario, (id_peli, rating_peli);

    
    public Users(String ratingDB){
        loadUser(ratingDB);
    }

    private void loadUser(String ratingDB) {
        
        try {
        FileReader fr = new FileReader(ratingDB);
        BufferedReader br = new BufferedReader(fr);

        String linea = br.readLine();
        linea = br.readLine(); // salta la primera linea
        while(linea != null){
            
          String[] valores_linea = linea.split(",");
          
          int id =Integer.valueOf(valores_linea[0]); 
        //  System.out.println(id);
        //  System.out.println(Integer.valueOf(valores_linea[1]));
        //  System.out.println(Float.valueOf(valores_linea[2]));
          HashMap<Integer,Float> ratings;
          
          if(users.containsKey(id)){
            ratings = users.get(id);
            ratings.put(Integer.valueOf(valores_linea[1]), Float.valueOf(valores_linea[2]));
            users.replace(id, ratings);
          }else{
            ratings = new HashMap<>();
            ratings.put(Integer.valueOf(valores_linea[1]), Float.valueOf(valores_linea[2]));
            users.put(id, ratings);
          }
          
          
          linea = br.readLine();
        }

        fr.close();
        }
        catch(Exception e) {
            System.out.println("Excepcion leyendo fichero "+ ratingDB + ": " + e);
        }
        
    }
    
    public float getRating(int userID,int movieID){
        return this.users.get(userID).get(movieID);
    }
    
    
    public void addUser(HashMap<Integer,Float> rating){
        this.users.put(this.users.size(), rating);
    }
    
    
    public float calcSim(int usr1, int usr2){

        
        HashMap<Integer,ArrayList<Float>> ratings;
        
        
        HashMap<Integer,Float> usr1_ratings = users.get(usr1);
        HashMap<Integer,Float> usr2_ratings = users.get(usr2);
        
        float media_usr1 = 0;
        float media_usr2 = 0;
        int i=0;
        
        for (Map.Entry<Integer, Float> entry : usr1_ratings.entrySet()){
            i++;
            media_usr1+=entry.getValue();
        }
        media_usr1 = media_usr1 / i;
        i=0;
        
        for (Map.Entry<Integer, Float> entry : usr2_ratings.entrySet()){
            i++;
            media_usr2+=entry.getValue();
        }
        media_usr2 = media_usr2 / i;
        
        
        float dividendo=0;
        float divisor1=0;
        float divisor2=0;
        
        for (Map.Entry<Integer, Float> entry : usr1_ratings.entrySet()) {
            //System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
            for (Map.Entry<Integer, Float> entry_2 : usr2_ratings.entrySet()){
                
                if(entry.getKey().equals(entry_2.getKey())){
                    
                    dividendo+=(entry.getValue()-media_usr1) * (entry_2.getValue() - media_usr2);
                    
                    divisor1+=(entry.getValue()-media_usr1)*(entry.getValue()-media_usr1);
                    divisor2+=(entry_2.getValue() - media_usr2)*(entry_2.getValue() - media_usr2);
                    
                }
                
                
            }
        }
        
        divisor1 =  (float) sqrt(divisor1);
        divisor2 = (float) sqrt(divisor2);
        
        float divisor = divisor1*divisor2;
        
        float pearson = dividendo / divisor;
        
        return pearson;
        
    }
    
    public ArrayList<Integer> getKusuariosSimilaresA(int idUsuario, int k){
        
        PriorityQueue<AbstractMap.SimpleEntry<Float, Integer>> pq = new PriorityQueue<AbstractMap.SimpleEntry<Float, Integer>>(new ComparatorPQ());
        
        for (Map.Entry<Integer, HashMap<Integer, Float>> entry : users.entrySet()){
            float sim = this.calcSim(idUsuario, entry.getKey());
            
           AbstractMap.SimpleEntry<Float, Integer> pair 
            = new AbstractMap.SimpleEntry<>(sim, entry.getKey());
           
           pq.add(pair);
        }
        
        ArrayList<Integer> kUsers = new ArrayList<>();
        
        for(int i=0; i < k ; i++)
        kUsers.add(pq.poll().getValue());
        
        return kUsers;
} 
    
    
    
}
