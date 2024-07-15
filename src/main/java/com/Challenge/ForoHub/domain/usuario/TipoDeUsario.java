package com.Challenge.ForoHub.domain.usuario;

public enum TipoDeUsario {
    INSTRUCTOR("Instructor"),
    ESTUDIANTE("Estudiante");

    private String tipoDeUsuario;

    TipoDeUsario(String tipoDeUsuario){
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public static TipoDeUsario fromString(String text){
        for(TipoDeUsario tipoDeUsario: TipoDeUsario.values()){
            if (tipoDeUsario.tipoDeUsuario.equalsIgnoreCase(text)){
                return tipoDeUsario;
            }
        }
        throw new IllegalArgumentException("No se ha encontrado el tipo de usario: " + text);
    }
}
