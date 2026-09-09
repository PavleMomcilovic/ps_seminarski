/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import domen.Smena;
import forme.UbaciSmenuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import komunikacija.Operacija;

/**
 *
 * @author pavle
 */
public class UbaciSmenuKontroler {
    private final UbaciSmenuForma forma;

    public UbaciSmenuKontroler(UbaciSmenuForma forma) {
        this.forma = forma;
        addActionListeners();
    }
    
    private void addActionListeners() {
        forma.getBtnUbaciSmenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamtiSmenu();
            }
        });
        
        forma.getBtnNazad().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forma.dispose();
            }
        });
    }
    
    private void zapamtiSmenu() {
        int pocetakSmene = ((Number) forma.getTxtPocetakSmene().getValue()).intValue();
        int krajSmene = ((Number) forma.getTxtKrajSmene().getValue()).intValue();
        
        Smena smena = new Smena();
        smena.setPocetakSmene(pocetakSmene);
        smena.setKrajSmene(krajSmene);
        
        boolean uspesno = posaljiSmenuNaServer(smena);
        
        if (uspesno) {
            JOptionPane.showMessageDialog(forma, "Sistem je zapamtio smenu.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            forma.dispose();
        } else {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da zapamti smenu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean posaljiSmenuNaServer(Smena smena) {
        try {
            Komunikacija.getInstanca().posaljiZahtev(Operacija.UBACI_SMENU, smena);
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da zapamti smenu: " + ex.getMessage(),
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
