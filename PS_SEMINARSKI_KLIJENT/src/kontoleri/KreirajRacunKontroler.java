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
import forme.FormaMod;
import forme.KreirajRacunForma;
import forme.model.ModelTabeleStavkeRacuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import komunikacija.Komunikacija;
import komunikacija.Operacija;

/**
 *
 * @author pavle
 */
public class KreirajRacunKontroler {

    private final KreirajRacunForma forma;
    private final FormaMod mod;
    private ModelTabeleStavkeRacuna modelTabele;

    private List<Prodavac> prodavci;
    private List<Kupac> kupci;
    private List<Gitara> gitare;

    private Racun racun;

    public KreirajRacunKontroler(KreirajRacunForma forma) {
        this(forma, FormaMod.KREIRAJ, null);
    }

    public KreirajRacunKontroler(KreirajRacunForma forma, FormaMod mod, Racun racunZaIzmenu) {
        this.forma = forma;
        this.mod = mod;
        this.racun = racunZaIzmenu;
        ucitajPocetneVrednosti();
        addActionListeners();
    }

    private void ucitajPocetneVrednosti() {
        forma.getTxtPopust().setText("0.0");
        forma.getTxtPopust().setEditable(false);

        forma.getTxtUkupanIznos().setText("0.0");
        forma.getTxtUkupanIznos().setEditable(false);

        forma.getTxtCenaStavke().setEditable(false);

        forma.getTxtIznosStavke().setEditable(false);

        forma.getCmbNacinPlacanja().setModel(
                new javax.swing.DefaultComboBoxModel<>(NacinPlacanja.values()));

        ucitajProdavce();
        ucitajKupce();
        ucitajGitare();

        if (mod == FormaMod.PROMENI) {
            forma.getBtnKreirajRacun().setText("Potvrdi promene");
            forma.getTxtDatumIzdavanja().setEditable(false);
            ucitajRacunZaIzmenu();
        } else {
            forma.getTxtDatumIzdavanja().setText(LocalDate.now().toString());

            modelTabele = new ModelTabeleStavkeRacuna(new ArrayList<>());
            forma.getTblStavkeRacuna().setModel(modelTabele);

            if (!kreirajRacunNaServeru()) {
                forma.getBtnDodajStavku().setEnabled(false);
                forma.getBtnObrisiStavku().setEnabled(false);
                forma.getBtnPromeniStavku().setEnabled(false);
                forma.getBtnKreirajRacun().setEnabled(false);
            }
        }
    }

    private void ucitajRacunZaIzmenu() {
        try {
            Racun kriterijum = new Racun();
            kriterijum.setIdRacun(racun.getIdRacun());
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.PRETRAZI_RACUN, kriterijum);
            if (rezultat == null) {
                JOptionPane.showMessageDialog(forma, "Sistem ne moze da pronadje racun za izmenu.",
                        "GRESKA", JOptionPane.ERROR_MESSAGE);
                onemoguciIzmenu();
                return;
            }
            racun = (Racun) rezultat;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da pronadje racun za izmenu: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            onemoguciIzmenu();
            return;
        }

        for (StavkaRacuna stavka : racun.getStavke()) {
            Gitara gitara = pronadjiGitaru(stavka.getGitara() != null ? stavka.getGitara().getIdGitara() : null);
            if (gitara != null) {
                stavka.setGitara(gitara);
            }
        }

        modelTabele = new ModelTabeleStavkeRacuna(new ArrayList<>(racun.getStavke()));
        forma.getTblStavkeRacuna().setModel(modelTabele);

        forma.getTxtDatumIzdavanja().setText(racun.getDatumIzdavanja() != null ? racun.getDatumIzdavanja().toString() : "");
        forma.getCmbNacinPlacanja().setSelectedItem(racun.getNacinPlacanja());
        forma.getTxtPopust().setText(String.valueOf(racun.getPopust()));

        if (racun.getProdavac() != null) {
            for (Prodavac p : prodavci) {
                if (p.getIdProdavac().equals(racun.getProdavac().getIdProdavac())) {
                    forma.getCmbProdavac().setSelectedItem(p);
                    break;
                }
            }
        }

        if (racun.getKupac() != null) {
            for (Kupac k : kupci) {
                if (k.getIdKupac().equals(racun.getKupac().getIdKupac())) {
                    forma.getCmbKupac().setSelectedItem(k);
                    break;
                }
            }
        }

        azurirajUkupanIznos();
    }

    private void onemoguciIzmenu() {
        forma.getBtnDodajStavku().setEnabled(false);
        forma.getBtnObrisiStavku().setEnabled(false);
        forma.getBtnPromeniStavku().setEnabled(false);
        forma.getBtnKreirajRacun().setEnabled(false);
    }

    private long sledeciRedniBroj() {
        long max = 0;
        for (StavkaRacuna s : modelTabele.getLista()) {
            if (s.getRb() != null && s.getRb() > max) {
                max = s.getRb();
            }
        }
        return max + 1;
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

        forma.getTblStavkeRacuna().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                ucitajIzabranuStavkuUPolja();
            }
        });

        forma.getBtnDodajStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajStavku();
            }
        });

        forma.getBtnObrisiStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiStavku();
            }
        });

        forma.getBtnPromeniStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeniStavku();
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

    private void ucitajIzabranuStavkuUPolja() {
        int redIndeks = forma.getTblStavkeRacuna().getSelectedRow();
        if (redIndeks < 0) {
            return;
        }

        StavkaRacuna stavka = modelTabele.getLista().get(redIndeks);
        Gitara gitara = pronadjiGitaru(stavka.getGitara() != null ? stavka.getGitara().getIdGitara() : null);
        if (gitara != null) {
            forma.getCmbGitara().setSelectedItem(gitara);
        }
        forma.getTxtKolicinaStavke().setText(String.valueOf(stavka.getKolicinaStavke()));
        azurirajIznosStavke();
    }

    private Gitara pronadjiGitaru(Long idGitara) {
        if (idGitara == null) {
            return null;
        }
        for (Gitara g : gitare) {
            if (idGitara.equals(g.getIdGitara())) {
                return g;
            }
        }
        return null;
    }

    private void dodajStavku() {
        if (racun == null) {
            JOptionPane.showMessageDialog(forma, "Racun nije zapocet na serveru.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
        stavka.setIdRacun(racun.getIdRacun());
        stavka.setGitara(izabranaGitara);
        stavka.setCenaStavke(cenaStavke);
        stavka.setKolicinaStavke(kolicinaStavke);
        stavka.setIznosStavke(iznosStavke);

        if (mod == FormaMod.PROMENI) {
            stavka.setRb(sledeciRedniBroj());
            modelTabele.addStavka(stavka);

            forma.getTxtKolicinaStavke().setText("");
            forma.getTxtIznosStavke().setText("");

            azurirajUkupanIznos();
            return;
        }

        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.UBACI_STAVKU_RACUNA, stavka);
            modelTabele.addStavka((StavkaRacuna) rezultat);

            forma.getTxtKolicinaStavke().setText("");
            forma.getTxtIznosStavke().setText("");

            azurirajUkupanIznos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da ubaci stavku racuna: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void obrisiStavku() {
        int redIndeks = forma.getTblStavkeRacuna().getSelectedRow();
        if (redIndeks < 0) {
            JOptionPane.showMessageDialog(forma, "Morate izabrati stavku za brisanje.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StavkaRacuna stavka = modelTabele.getLista().get(redIndeks);

        int potvrda = JOptionPane.showConfirmDialog(forma,
                "Da li zelite da obrisete izabranu stavku?",
                "POTVRDA BRISANJA", JOptionPane.YES_NO_OPTION);
        if (potvrda != JOptionPane.YES_OPTION) {
            return;
        }

        if (mod == FormaMod.PROMENI) {
            modelTabele.removeStavka(redIndeks);
            azurirajUkupanIznos();
            return;
        }

        try {
            Komunikacija.getInstanca().posaljiZahtev(Operacija.OBRISI_STAVKU_RACUNA, stavka);
            modelTabele.removeStavka(redIndeks);
            azurirajUkupanIznos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da obrise stavku racuna: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void promeniStavku() {
        int redIndeks = forma.getTblStavkeRacuna().getSelectedRow();
        if (redIndeks < 0) {
            JOptionPane.showMessageDialog(forma, "Morate izabrati stavku za promenu.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StavkaRacuna postojecaStavka = modelTabele.getLista().get(redIndeks);

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

        StavkaRacuna izmenjenaStavka = new StavkaRacuna();
        izmenjenaStavka.setIdRacun(postojecaStavka.getIdRacun());
        izmenjenaStavka.setRb(postojecaStavka.getRb());
        izmenjenaStavka.setGitara(izabranaGitara);
        izmenjenaStavka.setCenaStavke(cenaStavke);
        izmenjenaStavka.setKolicinaStavke(kolicinaStavke);
        izmenjenaStavka.setIznosStavke(iznosStavke);

        if (mod == FormaMod.PROMENI) {
            modelTabele.updateStavka(redIndeks, izmenjenaStavka);

            forma.getTxtKolicinaStavke().setText("");
            forma.getTxtIznosStavke().setText("");

            azurirajUkupanIznos();
            return;
        }

        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.PROMENI_STAVKU_RACUNA, izmenjenaStavka);
            modelTabele.updateStavka(redIndeks, (StavkaRacuna) rezultat);

            forma.getTxtKolicinaStavke().setText("");
            forma.getTxtIznosStavke().setText("");

            azurirajUkupanIznos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da promeni stavku racuna: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
        }
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
        if (racun == null) {
            JOptionPane.showMessageDialog(forma, "Racun nije zapocet na serveru.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

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

        if (mod == FormaMod.KREIRAJ) {
            racun.setDatumIzdavanja(LocalDate.now());
        }
        racun.setNacinPlacanja(izabraniNacinPlacanja);
        racun.setUkupanIznos(ukupanIznos);
        racun.setPopust(popust);
        racun.setProdavac(izabraniProdavac);
        racun.setKupac(izabraniKupac);
        racun.setStavke(modelTabele.getLista());

        boolean uspesno = posaljiRacunNaServer(racun);

        if (uspesno) {
            JOptionPane.showMessageDialog(forma,
                    mod == FormaMod.PROMENI ? "Sistem je izmenio racun." : "Sistem je zapamtio racun.",
                    "USPEH", JOptionPane.INFORMATION_MESSAGE);
            forma.dispose();
        } else {
            JOptionPane.showMessageDialog(forma,
                    mod == FormaMod.PROMENI ? "Sistem ne moze da izmeni racun." : "Sistem ne moze da zapamti racun.",
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
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

    private boolean kreirajRacunNaServeru() {
        Prodavac prodavac = (Prodavac) forma.getCmbProdavac().getSelectedItem();
        Kupac kupac = (Kupac) forma.getCmbKupac().getSelectedItem();
        NacinPlacanja nacinPlacanja = (NacinPlacanja) forma.getCmbNacinPlacanja().getSelectedItem();

        if (prodavac == null || kupac == null || nacinPlacanja == null) {
            JOptionPane.showMessageDialog(forma,
                    "Sistem ne moze da zapocne kreiranje racuna: nedostaje prodavac, kupac ili nacin placanja.",
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Racun noviRacun = new Racun();
        noviRacun.setDatumIzdavanja(LocalDate.now());
        noviRacun.setNacinPlacanja(nacinPlacanja);
        noviRacun.setUkupanIznos(0f);
        noviRacun.setPopust(0f);
        noviRacun.setProdavac(prodavac);
        noviRacun.setKupac(kupac);

        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.KREIRAJ_RACUN, noviRacun);
            racun = (rezultat instanceof Racun) ? (Racun) rezultat : noviRacun;
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapocne kreiranje racuna: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean posaljiRacunNaServer(Racun racun) {
        try {
            Komunikacija.getInstanca().posaljiZahtev(Operacija.PROMENI_RACUN, racun);
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti racun: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
