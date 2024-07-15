package com.Challenge.ForoHub.domain.curso;

public enum Categoria {
    FRONT_END("Front_end"),
    BACK_END("Back_end");

    private String categoria;

    Categoria(String categoria){
        this.categoria = categoria;
    }

    public static Categoria fromString(String text){
        for (Categoria categoria : Categoria.values()){
            if (categoria.categoria.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoría encontrada " + text);
    }
}
