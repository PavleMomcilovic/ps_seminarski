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

    }

    private void addActionListeners() {

        loginForma.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String username = loginForma.getTxtUsername().getText().trim();
                String password = String.valueOf(loginForma.getTxtPassword().getPassword());

                Komunikacija.getInstanca().konekcija();
                Prodavac ulogovani = Komunikacija.getInstanca().login(username, password);

                if (ulogovani == null) {
                    JOptionPane.showMessageDialog(loginForma, "Prijava na sistem neuspesna!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    Koordinator.getInstanca().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(loginForma, "Prijava na sistem uspesna!", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstanca().otvoriGlavnuFormu();
                    loginForma.dispose();
                }
            }
        });

    }

    public void otvoriFormu() {
        loginForma.setVisible(true);
    }
}
