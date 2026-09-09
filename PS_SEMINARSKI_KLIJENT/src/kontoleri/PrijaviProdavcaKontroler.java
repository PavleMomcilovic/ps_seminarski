/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import domen.Prodavac;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author pavle
 */
public class PrijaviProdavcaKontroler {

    private final LoginForma loginForma;

    public PrijaviProdavcaKontroler(LoginForma loginForma) {
        this.loginForma = loginForma;
        addActionListeners();
    }

    private void addActionListeners() {
        loginForma.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String username = loginForma.getTxtUsername().getText().trim();
                String password = String.valueOf(loginForma.getTxtPassword().getPassword()).trim();

                if (username.isBlank()) {
                    JOptionPane.showMessageDialog(loginForma,
                            "Korisničko ime ne sme biti prazno.",
                            "GREŠKA",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (password.isBlank()) {
                    JOptionPane.showMessageDialog(loginForma,
                            "Šifra ne sme biti prazna.",
                            "GREŠKA",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Komunikacija.getInstanca().konekcija();
                System.out.println("konektovano");
                Prodavac ulogovani = Komunikacija.getInstanca().login(username, password);
                System.out.println("ulogovano");

                if (ulogovani == null) {
                    JOptionPane.showMessageDialog(loginForma, "Korisničko ime i šifra nisu ispravni.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    Koordinator.getInstanca().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(loginForma, "Korisničko ime i šifra su ispravni", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Koordinator.getInstanca().otvoriGlavnuFormu();
                        loginForma.dispose();
                    } catch (Exception ex) {
                           JOptionPane.showMessageDialog(loginForma, "Ne može da se otvori glavna forma i meni.", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });
    }

    public void otvoriFormu() {
        loginForma.setVisible(true);
    }
}
