/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import forme.GlavnaForma;
import forme.KreirajRacunForma;
import forme.PrikaziRacunForma;
import forme.KreirajKupcaForma;
import forme.PrikaziKupcaForma;
import forme.UbaciSmenuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
        glavnaForma.getBtnKreirajRacun().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KreirajRacunForma forma = new KreirajRacunForma(glavnaForma, true);
                new KreirajRacunKontroler(forma);
                forma.setVisible(true);
            }
        });

        glavnaForma.getBtnPretraziRacun().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrikaziRacunForma forma = new PrikaziRacunForma(glavnaForma, false);
                new RacunKontroler(forma);
                forma.setVisible(true);
            }
        });

        glavnaForma.getBtnKreirajKupca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KreirajKupcaForma forma = new KreirajKupcaForma(glavnaForma, true);
                new KreirajKupcaKontroler(forma);
                forma.setVisible(true);
            }
        });

        glavnaForma.getBtnPretraziKupca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrikaziKupcaForma forma = new PrikaziKupcaForma(glavnaForma, false);
                new KupacKontroler(forma);
                forma.setVisible(true);
            }
        });

        glavnaForma.getBtnUbaciSmenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UbaciSmenuForma forma = new UbaciSmenuForma(glavnaForma, true);
                new UbaciSmenuKontroler(forma);
                forma.setVisible(true);
            }
        });
    }

    public void otvoriFormu() {
        glavnaForma.setExtendedState(JFrame.MAXIMIZED_BOTH);
        glavnaForma.setVisible(true);
    }
}
