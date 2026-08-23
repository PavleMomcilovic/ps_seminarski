/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.StavkaRacuna;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pavle
 */
public class ModelTabeleStavkeRacuna extends AbstractTableModel {
    private List<StavkaRacuna> lista;
    private final String[] kolone = {"RB", "Gitara", "Cena stavke", "Kolicina stavke", "Iznos stavke"};

    public ModelTabeleStavkeRacuna(List<StavkaRacuna> lista) {
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
        StavkaRacuna sr = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getGitara().getNaziv();
            case 2:
                return sr.getCenaStavke();
            case 3:
                return sr.getKolicinaStavke();
            case 4:
                return sr.getIznosStavke();
            default:
                return "N/A";
        }
    }

    public List<StavkaRacuna> getLista() {
        return lista;
    }

    public void setLista(List<StavkaRacuna> lista) {
        this.lista = lista;
    }
    
    public void addStavka(StavkaRacuna stavka) {
        lista.add(stavka);
        fireTableRowsInserted(lista.size() - 1, lista.size() - 1);
    }

    public float izracunajUkupanIznosStavki() {
        float suma = 0f;
        for (StavkaRacuna stavka : lista) {
            suma += stavka.getIznosStavke();
        }
        return suma;
    }
}
