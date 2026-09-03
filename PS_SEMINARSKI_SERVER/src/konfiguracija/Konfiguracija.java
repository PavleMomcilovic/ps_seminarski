/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavle
 */
public class Konfiguracija {
     private static final String PUTANJA_DO_KONFIGURACIJE = "C:\\Users\\Pavle\\OneDrive - Fakultet organizacionih nauka\\Documents\\GitHub\\ps_seminarski\\PS_SEMINARSKI_SERVER\\config\\config.properties";

     private static Konfiguracija instanca;
      private Properties konfiguracija;
    private Konfiguracija() {
         try {
             konfiguracija = new Properties();
             konfiguracija.load(new FileInputStream(PUTANJA_DO_KONFIGURACIJE));
         } catch (IOException ex) {
             ex.printStackTrace();
             Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
    public static Konfiguracija getInstanca() {
        if (instanca == null) {
            instanca = new Konfiguracija();
        }
        return instanca;
    }

    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }

    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }
    
    
    public void sacuvajIzmene() {
        try {
            konfiguracija.store(new FileOutputStream(PUTANJA_DO_KONFIGURACIJE), null);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            
        }
      }
    
    
    
    
}
