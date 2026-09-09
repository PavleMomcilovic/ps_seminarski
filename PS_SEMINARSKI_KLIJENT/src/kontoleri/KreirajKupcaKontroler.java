/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import domen.Kupac;
import domen.MuzickoObrazovanje;
import forme.FormaMod;
import forme.KreirajKupcaForma;
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
public class KreirajKupcaKontroler {

    private final KreirajKupcaForma forma;
    private final FormaMod mod;
    private Kupac kupacZaIzmenu;
    private List<MuzickoObrazovanje> muzickaObrazovanja;

    public KreirajKupcaKontroler(KreirajKupcaForma forma) {
        this(forma, FormaMod.KREIRAJ, null);
    }

    public KreirajKupcaKontroler(KreirajKupcaForma forma, FormaMod mod, Kupac kupacZaIzmenu) {
        this.forma = forma;
        this.mod = mod;
        this.kupacZaIzmenu = kupacZaIzmenu;
        ucitajPocetneVrednosti();
    }

    private void ucitajPocetneVrednosti() {
        muzickaObrazovanja = pribaviMuzickaObrazovanja();
        if (muzickaObrazovanja == null) {
            return;
        }

        forma.getCmbMuzickoObrazovanje().setModel(
                new javax.swing.DefaultComboBoxModel<>(muzickaObrazovanja.toArray()));
        forma.getCmbMuzickoObrazovanje().setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof MuzickoObrazovanje) {
                    setText(((MuzickoObrazovanje) value).getStepen().toString());
                }
                return this;
            }
        });

        addActionListeners();

        if (mod == FormaMod.PROMENI) {
            forma.getBtnKreirajKupca().setText("Potvrdi promene");
            popuniPoljaZaIzmenu();
        } else {
            try {
                kupacZaIzmenu = (Kupac) Komunikacija.getInstanca().posaljiZahtev(Operacija.KREIRAJ_KUPCA, new Kupac());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(forma, "Sistem ne može da kreira kupca: " + ex.getMessage(),
                        "GREŠKA", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(forma, "Sistem je kreirao kupca.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void popuniPoljaZaIzmenu() {
        forma.getTxtImePrezime().setText(kupacZaIzmenu.getImePrezime());

        if (kupacZaIzmenu.getMuzickoObr() != null) {
            for (MuzickoObrazovanje mo : muzickaObrazovanja) {
                if (mo.getIdMuzickoObr().equals(kupacZaIzmenu.getMuzickoObr().getIdMuzickoObr())) {
                    forma.getCmbMuzickoObrazovanje().setSelectedItem(mo);
                    break;
                }
            }
        }
    }

    private void addActionListeners() {
        forma.getBtnKreirajKupca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamtiKupca();
            }
        });

        forma.getBtnNazad().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiKupca();
            }
        });
    }

    private void zapamtiKupca() {
        String imePrezime = forma.getTxtImePrezime().getText().trim();
        MuzickoObrazovanje izabranoMuzickoObr = (MuzickoObrazovanje) forma.getCmbMuzickoObrazovanje().getSelectedItem();

        if (imePrezime.isEmpty() || izabranoMuzickoObr == null) {
            JOptionPane.showMessageDialog(forma, "Morate uneti ime i prezime i izabrati muzičko obrazovanje.",
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Kupac kupac = kupacZaIzmenu;
        kupac.setImePrezime(imePrezime);
        kupac.setMuzickoObr(izabranoMuzickoObr);

        boolean uspesno = mod == FormaMod.PROMENI ? posaljiIzmenuNaServer(kupac) : posaljiKupcaNaServer(kupac);

        if (uspesno) {
            JOptionPane.showMessageDialog(forma,
                    mod == FormaMod.PROMENI ? "Sistem je izmenio kupca." : "Sistem je zapamtio kupca.",
                    "USPEH", JOptionPane.INFORMATION_MESSAGE);
            forma.dispose();
        } else {
            JOptionPane.showMessageDialog(forma,
                    mod == FormaMod.PROMENI ? "Sistem ne može da izmeni kupca." : "Sistem ne može da zapamti kupca.",
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void obrisiKupca() {
        if (mod == FormaMod.PROMENI) {
            forma.dispose();
        } else if (mod == FormaMod.KREIRAJ) {
            int potvrda = JOptionPane.showConfirmDialog(forma,
                    "Da li želite da obrišete kupca?",
                    "POTVRDA BRISANJA", JOptionPane.YES_NO_OPTION);
            if (potvrda != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                Komunikacija.getInstanca().posaljiZahtev(Operacija.OBRISI_KUPCA, kupacZaIzmenu);
                JOptionPane.showMessageDialog(forma, "Sistem je obrisao kupca.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                forma.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(forma, "Sistem ne može da obriše kupca: " + ex.getMessage(),
                        "GREŠKA", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(forma, "Sistem ne može da kreira kupca: " + ex.getMessage(),
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private boolean posaljiKupcaNaServer(Kupac kupac) {
        try {
            Komunikacija.getInstanca().posaljiZahtev(Operacija.PROMENI_KUPCA, kupac);
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da zapamti kupca: " + ex.getMessage(),
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean posaljiIzmenuNaServer(Kupac kupac) {
        try {
            Komunikacija.getInstanca().posaljiZahtev(Operacija.PROMENI_KUPCA, kupac);
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da izmeni kupca: " + ex.getMessage(),
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
