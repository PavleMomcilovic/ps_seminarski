/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Kupac;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pavle
 */
public class ModelTabeleKupac extends AbstractTableModel {
    private List<Kupac> lista;
    private final String[] kolone = {"ID", "Ime i prezime", "Muzicko obrazovanje"};

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kupac k = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return k.getIdKupac();
            case 1:
                return k.getImePrezime();
            case 2:
                return k.getMuzickoObr();
            default:
                return "N/A";
        }
    }

    public List<Kupac> getLista() {
        return lista;
    }

    public void setLista(List<Kupac> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
}
