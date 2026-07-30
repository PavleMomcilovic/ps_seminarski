/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;

/**
 *
 * @author pavle
 */
public interface Repository<T> {
    List<T> uzmiSve(T param, String uslov) throws Exception;
    void dodaj(T param) throws Exception;
    void izmeni(T param) throws Exception;
    void obrisi(T param)throws Exception;
    T uzmiPoKljucu(T param) throws Exception;
    Long uzmiGenerisaniKljuc(T param) throws Exception;
}
