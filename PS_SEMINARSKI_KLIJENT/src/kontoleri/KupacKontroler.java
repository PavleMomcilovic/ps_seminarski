/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import domen.Kupac;
import domen.MuzickoObrazovanje;
import forme.FormaMod;
import forme.KreirajKupcaForma;
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

        ucitajMuzickaObrazovanjaZaPretragu();

        ucitajKupce();
    }

    private void ucitajMuzickaObrazovanjaZaPretragu() {
        List<MuzickoObrazovanje> muzickaObrazovanja = pribaviMuzickaObrazovanja();
        List<Object> stavke = new ArrayList<>();
        stavke.add(null);
        stavke.addAll(muzickaObrazovanja);

        forma.getCmbMuzickoObrazovanje().setModel(new javax.swing.DefaultComboBoxModel<>(stavke.toArray()));
        forma.getCmbMuzickoObrazovanje().setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(value instanceof MuzickoObrazovanje ? ((MuzickoObrazovanje) value).getStepen().toString() : "Sva muzička obrazovanja");
                return this;
            }
        });
    }

    private void ucitajKupce() {
        modelTabele.setLista(pribaviKupce(new Kupac()));
    }

    private void pretraziKupce() {
        Kupac kriterijum = new Kupac();

        String imePrezime = forma.getTxtImePrezime().getText().trim();
        if (!imePrezime.isEmpty()) {
            kriterijum.setImePrezime(imePrezime);
        }

        MuzickoObrazovanje izabranoMuzickoObr = (MuzickoObrazovanje) forma.getCmbMuzickoObrazovanje().getSelectedItem();
        if (izabranoMuzickoObr != null) {
            kriterijum.setMuzickoObr(izabranoMuzickoObr);
        }

        modelTabele.setLista(pretraziKupac(kriterijum));
    }

    private List<Kupac> pretraziKupac(Kupac kriterijum) {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.PRETRAZI_KUPCA, kriterijum);
            List<Kupac> lista = new ArrayList<>();
            if (rezultat != null) {
                lista.add((Kupac) rezultat);
            }
            dodajMuzickaObrazovanja(lista);
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da pretraži kupce: " + ex.getMessage(),
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    private void addActionListeners() {
        forma.getBtnPretraziKupca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretraziKupce();
            }
        });

        forma.getBtnObrisiKupca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiIzabranogKupca();
            }
        });

        forma.getBtnPromeniKupca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeniIzabranogKupca();
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
            JOptionPane.showMessageDialog(forma, "Morate izabrati kupca za brisanje.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Kupac kupac = modelTabele.getLista().get(redIndeks);

        int potvrda = JOptionPane.showConfirmDialog(forma,
                "Da li želite da obrišete kupca " + kupac.getImePrezime() + "?",
                "POTVRDA BRISANJA", JOptionPane.YES_NO_OPTION);
        if (potvrda != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Komunikacija.getInstanca().posaljiZahtev(Operacija.OBRISI_KUPCA, kupac);
            JOptionPane.showMessageDialog(forma, "Sistem je obrisao kupca.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            ucitajKupce();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da obriše kupca: " + ex.getMessage(),
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void promeniIzabranogKupca() {
        int redIndeks = forma.getTblKupac().getSelectedRow();
        if (redIndeks < 0) {
            JOptionPane.showMessageDialog(forma, "Morate izabrati kupca za promenu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Kupac izabraniKupac = modelTabele.getLista().get(redIndeks);

        KreirajKupcaForma kreirajKupcaForma = new KreirajKupcaForma((java.awt.Frame) forma.getOwner(), true);
        new KreirajKupcaKontroler(kreirajKupcaForma, FormaMod.PROMENI, izabraniKupac);
        kreirajKupcaForma.setVisible(true);

        ucitajKupce();
    }

    private List<Kupac> pribaviKupce(Kupac kriterijum) {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_KUPAC, kriterijum);
            List<Kupac> lista = new ArrayList<>();
            if (rezultat != null) {
                for (Object o : (List<?>) rezultat) {
                    lista.add((Kupac) o);
                }
            }
            dodajMuzickaObrazovanja(lista);
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da vrati listu kupaca: " + ex.getMessage(),
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(forma, "Sistem ne može da vrati listu muzičkih obrazovanja: " + ex.getMessage(),
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
}
