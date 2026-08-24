/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import domen.Racun;
import forme.PrikaziRacunForma;
import forme.model.ModelTabeleRacun;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import komunikacija.Operacija;

/**
 *
 * @author pavle
 */
public class RacunKontroler {
    private final PrikaziRacunForma forma;
    private ModelTabeleRacun modelTabele;

    public RacunKontroler(PrikaziRacunForma forma) {
        this.forma = forma;
        ucitajPocetneVrednosti();
        addActionListeners();
    }

    private void ucitajPocetneVrednosti() {
        modelTabele = new ModelTabeleRacun(new ArrayList<>());
        forma.getTblRacun().setModel(modelTabele);

        ucitajRacune();
    }

    private void ucitajRacune() {
        modelTabele.setLista(pribaviRacune());
    }

    private void addActionListeners() {
        forma.getBtnNazad().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forma.dispose();
            }
        });
    }

    private List<Racun> pribaviRacune() {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.PRETRAZI_RACUN, new Racun());
            List<Racun> lista = new ArrayList<>();
            if (rezultat != null) {
                for (Object o : (List<?>) rezultat) {
                    lista.add((Racun) o);
                }
            }
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da prikaze racune: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
}
