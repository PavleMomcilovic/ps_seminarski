/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import domen.Gitara;
import domen.Kupac;
import domen.NacinPlacanja;
import domen.Prodavac;
import domen.Racun;
import domen.StavkaRacuna;
import forme.KreirajRacunForma;
import forme.model.ModelTabeleStavkeRacuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import komunikacija.Operacija;

/**
 *
 * @author pavle
 */
public class KreirajRacunKontroler {

    private final KreirajRacunForma forma;
    private ModelTabeleStavkeRacuna modelTabele;

    private List<Prodavac> prodavci;
    private List<Kupac> kupci;
    private List<Gitara> gitare;

    public KreirajRacunKontroler(KreirajRacunForma forma) {
        this.forma = forma;
        ucitajPocetneVrednosti();
        addActionListeners();
    }

    private void ucitajPocetneVrednosti() {
        forma.getTxtDatumIzdavanja().setText(LocalDate.now().toString());

        forma.getTxtPopust().setText("0.0");
        forma.getTxtPopust().setEditable(false);

        forma.getTxtUkupanIznos().setText("0.0");
        forma.getTxtUkupanIznos().setEditable(false);

        forma.getTxtCenaStavke().setEditable(false);

        forma.getTxtIznosStavke().setEditable(false);

        modelTabele = new ModelTabeleStavkeRacuna(new ArrayList<>());
        forma.getTblStavkeRacuna().setModel(modelTabele);

        forma.getCmbNacinPlacanja().setModel(
                new javax.swing.DefaultComboBoxModel<>(NacinPlacanja.values()));

        ucitajProdavce();
        ucitajKupce();
        ucitajGitare();

        JOptionPane.showMessageDialog(forma, "Sistem je kreirao racun!", "USPEH", JOptionPane.INFORMATION_MESSAGE);
    }

    private void ucitajProdavce() {
        prodavci = pribaviProdavce();
        forma.getCmbProdavac().setModel(new javax.swing.DefaultComboBoxModel<>(prodavci.toArray()));
        forma.getCmbProdavac().setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Prodavac) {
                    setText(((Prodavac) value).getImePrezime());
                }
                return this;
            }
        });
    }

    private void ucitajKupce() {
        kupci = pribaviKupce();
        forma.getCmbKupac().setModel(new javax.swing.DefaultComboBoxModel<>(kupci.toArray()));
        forma.getCmbKupac().setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Kupac) {
                    setText(((Kupac) value).getImePrezime());
                }
                return this;
            }
        });
    }

    private void ucitajGitare() {
        gitare = pribaviGitare();
        forma.getCmbGitara().setModel(new javax.swing.DefaultComboBoxModel<>(gitare.toArray()));
        forma.getCmbGitara().setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Gitara) {
                    setText(((Gitara) value).getNaziv());
                }
                return this;
            }
        });
    }

    private void addActionListeners() {

        forma.getCmbKupac().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kupac izabraniKupac = (Kupac) forma.getCmbKupac().getSelectedItem();
                if (izabraniKupac != null && izabraniKupac.getMuzickoObr() != null) {
                    float popust = izabraniKupac.getMuzickoObr().getPopustPoStepenu();
                    forma.getTxtPopust().setText(String.valueOf(popust));
                } else {
                    forma.getTxtPopust().setText("0.0");
                }
                azurirajUkupanIznos();
            }
        });

        forma.getCmbGitara().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gitara izabranaGitara = (Gitara) forma.getCmbGitara().getSelectedItem();
                if (izabranaGitara != null) {
                    forma.getTxtCenaStavke().setText(String.valueOf(izabranaGitara.getCena()));
                }
                azurirajIznosStavke();
            }
        });

        forma.getTxtKolicinaStavke().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                azurirajIznosStavke();
            }
        });

        forma.getBtnDodajStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajStavku();
            }
        });

        forma.getBtnKreirajRacun().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kreirajRacun();
            }
        });

        forma.getBtnNazad().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forma.dispose();
            }
        });
    }

    private void azurirajIznosStavke() {
        try {
            float cena = Float.parseFloat(forma.getTxtCenaStavke().getText().trim());
            int kolicina = Integer.parseInt(forma.getTxtKolicinaStavke().getText().trim());
            float iznos = cena * kolicina;
            forma.getTxtIznosStavke().setText(String.valueOf(iznos));
        } catch (NumberFormatException ex) {
            forma.getTxtIznosStavke().setText("");
        }
    }

    private void dodajStavku() {
        Gitara izabranaGitara = (Gitara) forma.getCmbGitara().getSelectedItem();
        if (izabranaGitara == null) {
            JOptionPane.showMessageDialog(forma, "Morate izabrati gitaru.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int kolicinaStavke;
        try {
            kolicinaStavke = Integer.parseInt(forma.getTxtKolicinaStavke().getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(forma, "Kolicina stavke mora biti ceo broj.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (kolicinaStavke < 1) {
            JOptionPane.showMessageDialog(forma, "Kolicina mora biti najmanje 1.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        float cenaStavke = izabranaGitara.getCena();
        float iznosStavke = cenaStavke * kolicinaStavke;

        StavkaRacuna stavka = new StavkaRacuna();
        stavka.setRb((long) (modelTabele.getLista().size() + 1));
        stavka.setGitara(izabranaGitara);
        stavka.setCenaStavke(cenaStavke);
        stavka.setKolicinaStavke(kolicinaStavke);
        stavka.setIznosStavke(iznosStavke);

        modelTabele.addStavka(stavka);

        forma.getTxtKolicinaStavke().setText("");
        forma.getTxtIznosStavke().setText("");

        azurirajUkupanIznos();
    }

    private void azurirajUkupanIznos() {
        float suma = modelTabele.izracunajUkupanIznosStavki();
        float popust = 0f;
        try {
            popust = Float.parseFloat(forma.getTxtPopust().getText().trim());
        } catch (NumberFormatException ignored) {
        }
        float ukupanIznos = suma * (1 - popust);
        forma.getTxtUkupanIznos().setText(String.valueOf(ukupanIznos));
    }

    private void kreirajRacun() {
        Prodavac izabraniProdavac = (Prodavac) forma.getCmbProdavac().getSelectedItem();
        Kupac izabraniKupac = (Kupac) forma.getCmbKupac().getSelectedItem();
        NacinPlacanja izabraniNacinPlacanja = (NacinPlacanja) forma.getCmbNacinPlacanja().getSelectedItem();

        if (izabraniProdavac == null || izabraniKupac == null || izabraniNacinPlacanja == null) {
            JOptionPane.showMessageDialog(forma, "Morate izabrati prodavca, kupca i nacin placanja.",
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (modelTabele.getLista().isEmpty()) {
            JOptionPane.showMessageDialog(forma, "Racun mora imati bar jednu stavku.",
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        float popust = Float.parseFloat(forma.getTxtPopust().getText().trim());
        float ukupanIznos = Float.parseFloat(forma.getTxtUkupanIznos().getText().trim());

        Racun racun = new Racun();
        racun.setDatumIzdavanja(LocalDate.now());
        racun.setNacinPlacanja(izabraniNacinPlacanja);
        racun.setUkupanIznos(ukupanIznos);
        racun.setPopust(popust);
        racun.setProdavac(izabraniProdavac);
        racun.setKupac(izabraniKupac);
        racun.setStavke(modelTabele.getLista());

        boolean uspesno = posaljiRacunNaServer(racun);

        if (uspesno) {
            JOptionPane.showMessageDialog(forma, "Sistem je zapamtio racun.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            forma.dispose();
        } else {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti racun.", "GRESKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<Prodavac> pribaviProdavce() {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_PRODAVAC, new Prodavac());
            List<Prodavac> lista = new ArrayList<>();
            for (Object o : (List<?>) rezultat) {
                lista.add((Prodavac) o);
            }
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Greska prilikom ucitavanja prodavaca: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    private List<Kupac> pribaviKupce() {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_KUPAC, new Kupac());
            List<Kupac> lista = new ArrayList<>();
            for (Object o : (List<?>) rezultat) {
                lista.add((Kupac) o);
            }
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Greska prilikom ucitavanja kupaca: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    private List<Gitara> pribaviGitare() {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_GITARA, new Gitara());
            List<Gitara> lista = new ArrayList<>();
            for (Object o : (List<?>) rezultat) {
                lista.add((Gitara) o);
            }
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Greska prilikom ucitavanja gitara: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    private boolean posaljiRacunNaServer(Racun racun) {
        try {
            Komunikacija.getInstanca().posaljiZahtev(Operacija.KREIRAJ_RACUN, racun);
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti racun: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
