/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import domen.Gitara;
import domen.VrstaGitare;
import forme.PrikaziGitaruForma;
import forme.model.ModelTabeleGitara;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import komunikacija.Operacija;

/**
 *
 * @author Pavle
 */
public class GitaraKontroler {
    private final PrikaziGitaruForma forma;
    private ModelTabeleGitara modelTabele;
    private Gitara izabranaGitara;

    public GitaraKontroler(PrikaziGitaruForma forma) {
        this.forma = forma;
        ucitajPocetneVrednosti();
        addActionListeners();
    }

    private void ucitajPocetneVrednosti() {
        modelTabele = new ModelTabeleGitara();
        modelTabele.setLista(new ArrayList<>());
        forma.getTblGitara().setModel(modelTabele);

        List<Object> vrste = new ArrayList<>();
        vrste.add(null);
        vrste.addAll(java.util.Arrays.asList(VrstaGitare.values()));
        forma.getCmbVrsta().setModel(new javax.swing.DefaultComboBoxModel<>(vrste.toArray()));
        forma.getCmbVrsta().setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(value instanceof VrstaGitare ? value.toString() : "Sve vrste");
                return this;
            }
        });

        ucitajGitare();
    }

    private void ucitajGitare() {
        modelTabele.setLista(pribaviGitare(new Gitara()));
    }

    private void pretraziGitare() {
        Gitara kriterijum = new Gitara();

        String naziv = forma.getTxtNaziv().getText().trim();
        if (!naziv.isEmpty()) {
            kriterijum.setNaziv(naziv);
        }

        String cena = forma.getTxtCena().getText().trim();
        if (!cena.isEmpty()) {
            try {
                kriterijum.setCena(Float.parseFloat(cena));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(forma, "Cena mora biti broj.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        VrstaGitare izabranaVrsta = (VrstaGitare) forma.getCmbVrsta().getSelectedItem();
        if (izabranaVrsta != null) {
            kriterijum.setVrsta(izabranaVrsta);
        }

        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_GITARA, kriterijum);
            List<Gitara> lista = new ArrayList<>();
            if (rezultat != null) {
                for (Object o : (List<?>) rezultat) {
                    lista.add((Gitara) o);
                }
            }
            modelTabele.setLista(lista);

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe gitare po zadatim kriterijumima.",
                        "GREŠKA", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(forma, "Sistem je našao gitare po zadatim kriterijumima",
                        "USPEH", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe gitare po zadatim kriterijumima: " + ex.getMessage(),
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionListeners() {
        forma.getBtnPretraziGitare().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretraziGitare();
            }
        });

        forma.getBtnIzaberi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izaberiGitaru();
            }
        });

        forma.getBtnNazad().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forma.dispose();
            }
        });
    }

    private void izaberiGitaru() {
        int redIndeks = forma.getTblGitara().getSelectedRow();
        if (redIndeks < 0) {
            JOptionPane.showMessageDialog(forma, "Morate izabrati gitaru.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        izabranaGitara = modelTabele.getLista().get(redIndeks);
        forma.dispose();
    }

    public Gitara getIzabranaGitara() {
        return izabranaGitara;
    }

    private List<Gitara> pribaviGitare(Gitara kriterijum) {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_GITARA, kriterijum);
            List<Gitara> lista = new ArrayList<>();
            if (rezultat != null) {
                for (Object o : (List<?>) rezultat) {
                    lista.add((Gitara) o);
                }
            }
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da vrati listu gitara: " + ex.getMessage(),
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
}
