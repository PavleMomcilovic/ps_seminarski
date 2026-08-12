/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author pavle
 */
public class Posiljalac {

    private Socket socket;
    ObjectOutputStream out;

    public Posiljalac(Socket socket) {
        this.socket = socket;
    }

    public void posalji(Object obj) {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(obj);
            out.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void zatvori() {
        if (out != null)
            try {
                out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
