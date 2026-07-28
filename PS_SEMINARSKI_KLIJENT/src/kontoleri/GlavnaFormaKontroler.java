/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import forme.GlavnaForma;

/**
 *
 * @author pavle
 */
public class GlavnaFormaKontroler {
    private final GlavnaForma glavnaForma;

    public GlavnaFormaKontroler(GlavnaForma glavnaForma) {
        this.glavnaForma = glavnaForma;
        addActionListeners();
    }
    
    private void addActionListeners() {
        
    }
    
    public void otvoriFormu() {
        glavnaForma.setVisible(true);
    }
}
