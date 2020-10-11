//package com.company;

public class Exemplo implements Comparable<Exemplo> {
    private String classe;
    private Double distancia;

    public Exemplo(String classe, Double distancia) {
        this.classe = classe;
        this.distancia = distancia;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    @Override
    public int compareTo(Exemplo e) {
        return this.getDistancia().compareTo(e.getDistancia());

    }
}
