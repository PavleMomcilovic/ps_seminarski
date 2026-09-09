/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Racun;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pavle
 */
public class ModelTabeleRacun extends AbstractTableModel {
    private List<Racun> lista;
    private final String[] kolone = {"ID", "Datum izdavanja", "Način plaćanja", "Ukupan iznos", "Popust", "Prodavac", "Kupac"};

    public ModelTabeleRacun(List<Racun> lista) {
        this.lista = lista;
    }
    
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
        Racun r = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return r.getIdRacun();
            case 1:
                return r.getDatumIzdavanja();
            case 2:
                return r.getNacinPlacanja();
            case 3:
                return r.getUkupanIznos();
            case 4:
                return r.getPopust();
            case 5:
                return r.getProdavac().getImePrezime();
            case 6:
                return r.getKupac().getImePrezime();
            default:
                return "N/A";
        }
    }

    public List<Racun> getLista() {
        return lista;
    }

    public void setLista(List<Racun> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
}
