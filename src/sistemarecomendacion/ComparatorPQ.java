/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarecomendacion;

import java.util.AbstractMap;
import java.util.Comparator;

/**
 *
 * @author ancaor
 */
public class ComparatorPQ implements Comparator<AbstractMap.SimpleEntry<Float, Integer>>{ 
              
            // Overriding compare()method of Comparator  
                        // for descending order of cgpa 
            public int compare(AbstractMap.SimpleEntry<Float, Integer> s1, AbstractMap.SimpleEntry<Float, Integer> s2) { 
                if (s1.getKey() < s2.getKey()) 
                    return 1; 
                else if (s1.getKey() > s2.getKey()) 
                    return -1; 
                                return 0; 
                } 
        }
