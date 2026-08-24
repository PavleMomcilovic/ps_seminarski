/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import domen.Kupac;
import domen.MuzickoObrazovanje;
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
    private List<MuzickoObrazovanje> muzickaObrazovanja;

    public KreirajKupcaKontroler(KreirajKupcaForma forma) {
        this.forma = forma;
        kreirajKupca();
    }

    private void kreirajKupca() {
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

        JOptionPane.showMessageDialog(forma, "Sistem je kreirao kupca.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
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
                forma.dispose();
            }
        });
    }

    private void zapamtiKupca() {
        String imePrezime = forma.getTxtImePrezime().getText().trim();
        MuzickoObrazovanje izabranoMuzickoObr = (MuzickoObrazovanje) forma.getCmbMuzickoObrazovanje().getSelectedItem();

        if (imePrezime.isEmpty() || izabranoMuzickoObr == null) {
            JOptionPane.showMessageDialog(forma, "Morate uneti ime i prezime i izabrati muzicko obrazovanje.",
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Kupac kupac = new Kupac();
        kupac.setImePrezime(imePrezime);
        kupac.setMuzickoObr(izabranoMuzickoObr);

        boolean uspesno = posaljiKupcaNaServer(kupac);

        if (uspesno) {
            JOptionPane.showMessageDialog(forma, "Sistem je zapamtio kupca.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            forma.dispose();
        } else {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti kupca.", "GRESKA", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da kreira kupca: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private boolean posaljiKupcaNaServer(Kupac kupac) {
        try {
            Komunikacija.getInstanca().posaljiZahtev(Operacija.KREIRAJ_KUPCA, kupac);
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti kupca: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
