package com.Challenge.ForoHub.domain.topico;

public enum EstadoDelTopico {
    SOLUCIONADO("Solucionado"),
    SIN_SOLUCION("Sin_solucion");

    private String estado;

    EstadoDelTopico(String estado){
        this.estado = estado;
    }

    public static EstadoDelTopico fromString(String text){
        for(EstadoDelTopico estado : EstadoDelTopico.values()){
            if(estado.estado.equalsIgnoreCase(text)){
                return estado;
            }
        }
        throw new IllegalArgumentException("Ningún estado encontrado: " + text);
    }
}
