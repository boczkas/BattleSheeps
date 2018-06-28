package pl.jakubowskiprzemyslaw.tajgertim;

import java.util.ArrayList;
import java.util.List;

public class Lista {
    List<String> lista;

    public Lista() {
        lista = new ArrayList<>();
        lista.add("aa");
    }

    public List<String> getLista() {
        return lista;
    }

    public void addToList(String text){
        lista.add(text);
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }
}
