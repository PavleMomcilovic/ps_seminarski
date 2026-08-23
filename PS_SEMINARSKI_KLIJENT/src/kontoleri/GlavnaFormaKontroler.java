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
                forma.setVisible(true);
            }
        });

        glavnaForma.getBtnKreirajKupca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KreirajKupcaForma forma = new KreirajKupcaForma(glavnaForma, true);
                forma.setVisible(true);
            }
        });

        glavnaForma.getBtnPretraziKupca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrikaziKupcaForma forma = new PrikaziKupcaForma(glavnaForma, false);
                forma.setVisible(true);
            }
        });

        glavnaForma.getBtnUbaciSmenu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UbaciSmenuForma forma = new UbaciSmenuForma(glavnaForma, true);
                forma.setVisible(true);
            }
        });
    }

    public void otvoriFormu() {
        glavnaForma.setVisible(true);
    }
}
