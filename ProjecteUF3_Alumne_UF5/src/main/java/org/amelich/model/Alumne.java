package org.amelich.model;

import java.io.Serializable;
import java.util.Collection;


/**
 * Classe Alumne que implementa Serializable.
 * Representa un alumne amb el seu nom, nota, estat de FCT i els seus esmorzars.
 */
public class Alumne implements Serializable {
    private String nomCognom;
    private double nota;
    private boolean fct;

    private Collection<Esmorzar> esmorzars;

    /**
     * Constructor de la classe Alumne.
     * @param nomCognom Nom i cognoms de l'alumne.
     * @param nota Nota de l'alumne.
     * @param fct Estat de FCT de l'alumne.
     * @param esmorzars Col·lecció d'esmorzars de l'alumne.
     */
    public Alumne(String nomCognom, double nota, boolean fct, Collection<Esmorzar> esmorzars) {
        this.nomCognom = nomCognom;
        this.nota = nota;
        this.fct = fct;
        this.esmorzars = esmorzars;
    }

    public Collection<Alumne.Esmorzar> getEsmorzars() {
        return esmorzars;
    }

    private void setEsmorzars(Collection<Esmorzar> esmorzars) {
        this.esmorzars = esmorzars;
    }

    public String getNomCognom() {
        return nomCognom;
    }

    public void setNomCognom(String nomCognom) {
        this.nomCognom = nomCognom;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public boolean isFct() {
        return fct;
    }

    public void setFct(boolean fct) {
        this.fct = fct;
    }


    /**
     * Classe Esmorzar que implementa Comparable i Serializable.
     * Representa un esmorzar amb el seu producte i quantitat.
     */
    public static class Esmorzar implements Comparable<Esmorzar>, Serializable {
        public int compareTo(Esmorzar o) {
            return this.producte.compareTo(o.getProducte());
        }

        public static enum Producte {
            A01("Entrepa de pernil i formatge"), A02("Sandwich de pollastre"),
            A03("Croissant de xocolata"), A04("Empanada de carn"),
            A05("Ensalada de tonyina"), A06("Poma"), A07("Iogurt natural"),
            A08("Barreta de cereals"), A09("Suc de taronja"),
            A10("Batut de fruites"), A11("Pastís"),
            A12("Truita de patates"), A13("Patates fregides"), A14("Beguda");
            private String nom;

            private Producte(String nom) {
                this.nom = nom;
            }

            public String getNom() {
                return nom;
            }

            @Override
            public String toString() {
                return this.name()+" - " +nom;
            }
        }

        private Esmorzar.Producte producte;
        private int quantitat;

        public Esmorzar(Esmorzar.Producte producte, int quantitat) {
            this.producte = producte;
            this.quantitat = quantitat;
        }

        public Esmorzar.Producte getProducte() {
            return producte;
        }

        public void setProducte(Esmorzar.Producte producte) {
            this.producte = producte;
        }

        public int getQuantitat() {
            return quantitat;
        }

        public void setQuantitat(int quantitat) {
            this.quantitat = quantitat;
        }

    }
}