/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author pavle
 */
public class Primalac {

    private Socket socket;
    private ObjectInputStream in;

    public Primalac(Socket socket) {
        this.socket = socket;
    }

    public Object primi() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void zatvori() {
        if (in != null)
            try {
                in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
