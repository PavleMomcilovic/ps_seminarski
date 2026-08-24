/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import domen.Kupac;
import domen.MuzickoObrazovanje;
import forme.PrikaziKupcaForma;
import forme.model.ModelTabeleKupac;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import komunikacija.Operacija;

/**
 *
 * @author pavle
 */
public class KupacKontroler {
    private final PrikaziKupcaForma forma;
    private ModelTabeleKupac modelTabele;

    public KupacKontroler(PrikaziKupcaForma forma) {
        this.forma = forma;
        ucitajPocetneVrednosti();
        addActionListeners();
    }

    private void ucitajPocetneVrednosti() {
        modelTabele = new ModelTabeleKupac();
        modelTabele.setLista(new ArrayList<>());
        forma.getTblKupac().setModel(modelTabele);

        ucitajKupce();
    }

    private void ucitajKupce() {
        modelTabele.setLista(pribaviKupce());
    }

    private void addActionListeners() {
        forma.getBtnObrisiKupca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiIzabranogKupca();
            }
        });

        forma.getBtnNazad().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forma.dispose();
            }
        });
    }

    private void obrisiIzabranogKupca() {
        int redIndeks = forma.getTblKupac().getSelectedRow();
        if (redIndeks < 0) {
            JOptionPane.showMessageDialog(forma, "Morate izabrati kupca za brisanje.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Kupac kupac = modelTabele.getLista().get(redIndeks);

        int potvrda = JOptionPane.showConfirmDialog(forma,
                "Da li zelite da obrisete kupca " + kupac.getImePrezime() + "?",
                "POTVRDA BRISANJA", JOptionPane.YES_NO_OPTION);
        if (potvrda != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Komunikacija.getInstanca().posaljiZahtev(Operacija.OBRISI_KUPCA, kupac);
            JOptionPane.showMessageDialog(forma, "Sistem je obrisao kupca.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            ucitajKupce();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da obrise kupca: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<Kupac> pribaviKupce() {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_KUPAC, new Kupac());
            List<Kupac> lista = new ArrayList<>();
            if (rezultat != null) {
                for (Object o : (List<?>) rezultat) {
                    lista.add((Kupac) o);
                }
            }
            dodajMuzickaObrazovanja(lista);
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da vrati listu kupaca: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    private void dodajMuzickaObrazovanja(List<Kupac> kupci) {
        List<MuzickoObrazovanje> muzickaObrazovanja = pribaviMuzickaObrazovanja();
        Map<Long, MuzickoObrazovanje> poId = new HashMap<>();
        
        for (MuzickoObrazovanje mo : muzickaObrazovanja) {
            poId.put(mo.getIdMuzickoObr(), mo);
        }

        for (Kupac kupac : kupci) {
            if (kupac.getMuzickoObr() != null) {
                MuzickoObrazovanje puno = poId.get(kupac.getMuzickoObr().getIdMuzickoObr());
                if (puno != null) {
                    kupac.setMuzickoObr(puno);
                }
            }
        }
    }

    private List<MuzickoObrazovanje> pribaviMuzickaObrazovanja() {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_MUZICKO_OBRAZOVANJE, new MuzickoObrazovanje());
            List<MuzickoObrazovanje> lista = new ArrayList<>();
            for (Object o : (List<?>) rezultat) {
                lista.add((MuzickoObrazovanje) o);
            }
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da vrati listu muzickih obrazovanja: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
}
