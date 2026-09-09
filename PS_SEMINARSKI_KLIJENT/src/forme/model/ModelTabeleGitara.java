/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Gitara;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Pavle
 */
public class ModelTabeleGitara extends AbstractTableModel {
    private List<Gitara> lista;
    private final String[] kolone = {"ID", "Naziv", "Cena", "Vrsta"};

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
        Gitara g = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return g.getIdGitara();
            case 1:
                return g.getNaziv();
            case 2:
                return g.getCena();
            case 3:
                return g.getVrsta();
            default:
                return "N/A";
        }
    }

    public List<Gitara> getLista() {
        return lista;
    }

    public void setLista(List<Gitara> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
}
