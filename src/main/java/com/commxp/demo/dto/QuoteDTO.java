package com.commxp.demo.dto;

public class QuoteDTO {
    private String q; // Quote
    private String a; // Author
    private String c; // Identifier
    private String h; // HTML representation (optional)

    // Constructors, getters, and setters

    // Getters and setters for the properties
    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return "QuoteDTO{" +
                "q='" + q + '\'' +
                ", a='" + a + '\'' +
                ", c='" + c + '\'' +
                ", h='" + h + '\'' +
                '}';
    }
}
