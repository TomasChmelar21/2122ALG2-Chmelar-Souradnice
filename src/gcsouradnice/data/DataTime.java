/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice.data;

/**
 *
 * @author tomch
 */
import java.time.LocalDate;    
public class DataTime {    
    /**
     * 
     * @return String with today date 
     */
  public static String today(){
      return "Today date: " + LocalDate.now(); 
  }
  /**
   * 
   * @return String with tomorrow date 
   */
  public static String tomorrow(){
      return "Tomorrow date: " + LocalDate.now().plusDays(1); 
  }
  
}    