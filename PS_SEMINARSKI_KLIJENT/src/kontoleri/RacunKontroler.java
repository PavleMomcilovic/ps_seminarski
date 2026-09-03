/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import domen.Kupac;
import domen.NacinPlacanja;
import domen.Prodavac;
import domen.Racun;
import forme.FormaMod;
import forme.KreirajRacunForma;
import forme.PrikaziRacunForma;
import forme.model.ModelTabeleRacun;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
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

        ucitajProdavceZaPretragu();
        ucitajKupceZaPretragu();
        ucitajNacineZaPretragu();

        ucitajRacune();
    }

    private void ucitajProdavceZaPretragu() {
        List<Prodavac> prodavci = pribaviProdavce();
        List<Object> stavke = new ArrayList<>();
        stavke.add(null);
        stavke.addAll(prodavci);

        forma.getCmbProdavac().setModel(new javax.swing.DefaultComboBoxModel<>(stavke.toArray()));
        forma.getCmbProdavac().setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(value instanceof Prodavac ? ((Prodavac) value).getImePrezime() : "Svi prodavci");
                return this;
            }
        });
    }

    private void ucitajKupceZaPretragu() {
        List<Kupac> kupci = pribaviKupce();
        List<Object> stavke = new ArrayList<>();
        stavke.add(null);
        stavke.addAll(kupci);

        forma.getCmbKupac().setModel(new javax.swing.DefaultComboBoxModel<>(stavke.toArray()));
        forma.getCmbKupac().setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(value instanceof Kupac ? ((Kupac) value).getImePrezime() : "Svi kupci");
                return this;
            }
        });
    }

    private void ucitajNacineZaPretragu() {
        List<Object> stavke = new ArrayList<>();
        stavke.add(null);
        stavke.addAll(Arrays.asList(NacinPlacanja.values()));

        forma.getCmbNacinPlacanja().setModel(new javax.swing.DefaultComboBoxModel<>(stavke.toArray()));
        forma.getCmbNacinPlacanja().setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(value != null ? value.toString() : "Svi nacini placanja");
                return this;
            }
        });
    }

    private void ucitajRacune() {
        modelTabele.setLista(pribaviRacune(new Racun()));
    }

    private void pretraziRacune() {
        Racun kriterijum = new Racun();

        Prodavac izabraniProdavac = (Prodavac) forma.getCmbProdavac().getSelectedItem();
        if (izabraniProdavac != null) {
            kriterijum.setProdavac(izabraniProdavac);
        }

        Kupac izabraniKupac = (Kupac) forma.getCmbKupac().getSelectedItem();
        if (izabraniKupac != null) {
            kriterijum.setKupac(izabraniKupac);
        }

        NacinPlacanja izabraniNacinPlacanja = (NacinPlacanja) forma.getCmbNacinPlacanja().getSelectedItem();
        if (izabraniNacinPlacanja != null) {
            kriterijum.setNacinPlacanja(izabraniNacinPlacanja);
        }

        String datum = forma.getTxtDatumIzdavanja().getText().trim();
        if (!datum.isEmpty()) {
            try {
                kriterijum.setDatumIzdavanja(LocalDate.parse(datum));
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(forma, "Datum izdavanja mora biti u formatu GGGG-MM-DD.",
                        "GRESKA", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String popust = forma.getTxtPopust().getText().trim();
        if (!popust.isEmpty()) {
            try {
                kriterijum.setPopust(Float.parseFloat(popust));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(forma, "Popust mora biti broj.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String ukupanIznos = forma.getTxtUkupanIznos().getText().trim();
        if (!ukupanIznos.isEmpty()) {
            try {
                kriterijum.setUkupanIznos(Float.parseFloat(ukupanIznos));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(forma, "Ukupan iznos mora biti broj.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        modelTabele.setLista(pretraziRacun(kriterijum));
    }

    private List<Racun> pretraziRacun(Racun kriterijum) {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.PRETRAZI_RACUN, kriterijum);
            List<Racun> lista = new ArrayList<>();
            if (rezultat != null) {
                lista.add((Racun) rezultat);
            }
            dodajKupceProdavce(lista);
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da pretrazi racune: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    private void addActionListeners() {
        forma.getBtnPretraziRacun().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretraziRacune();
            }
        });

        forma.getBtnPromeniRacun().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeniIzabraniRacun();
            }
        });

        forma.getBtnNazad().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forma.dispose();
            }
        });
    }

    private void promeniIzabraniRacun() {
        int redIndeks = forma.getTblRacun().getSelectedRow();
        if (redIndeks < 0) {
            JOptionPane.showMessageDialog(forma, "Morate izabrati racun za promenu.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Racun izabraniRacun = modelTabele.getLista().get(redIndeks);

        KreirajRacunForma kreirajRacunForma = new KreirajRacunForma((java.awt.Frame) forma.getOwner(), true);
        new KreirajRacunKontroler(kreirajRacunForma, FormaMod.PROMENI, izabraniRacun);
        kreirajRacunForma.setVisible(true);

        ucitajRacune();
    }

    private List<Racun> pribaviRacune(Racun kriterijum) {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_RACUN, kriterijum);
            List<Racun> lista = new ArrayList<>();
            if (rezultat != null) {
                for (Object o : (List<?>) rezultat) {
                    lista.add((Racun) o);
                }
            }
            dodajKupceProdavce(lista);
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da vrati listu racuna: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
    
    private void dodajKupceProdavce(List<Racun> racuni) {
        List<Kupac> kupci = pribaviKupce();
        Map<Long, Kupac> poIdK = new HashMap<>();
        
        List<Prodavac> prodavci = pribaviProdavce();
        Map<Long, Prodavac> poIdP = new HashMap<>();
        
        for (Kupac k : kupci) {
            poIdK.put(k.getIdKupac(), k);
        }
        
        for (Prodavac p : prodavci) {
            poIdP.put(p.getIdProdavac(), p);
        }
        
        for (Racun r : racuni) {
            if (r.getKupac() != null) {
                Kupac kupac = poIdK.get(r.getKupac().getIdKupac());
                if (kupac != null)
                    r.setKupac(kupac);
            }
            
            if (r.getProdavac() != null) {
                Prodavac prodavac = poIdP.get(r.getProdavac().getIdProdavac());
                if (prodavac != null)
                    r.setProdavac(prodavac);
            }
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
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da vrati listu kupaca: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
    
    private List<Prodavac> pribaviProdavce() {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_PRODAVAC, new Prodavac());
            List<Prodavac> lista = new ArrayList<>();
            if (rezultat != null) {
                for (Object o : (List<?>) rezultat) {
                    lista.add((Prodavac) o);
                }
            }
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da vrati listu prodavaca: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
}
