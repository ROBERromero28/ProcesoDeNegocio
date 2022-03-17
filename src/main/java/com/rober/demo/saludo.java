package com.rober.demo;

import javax.print.DocFlavor;

public class saludo {

    private final long id;
    private final String contenido;

    public saludo(long id, String contenido) {
        this.id = id;
        this.contenido=contenido;
    }

    public long getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }
}
