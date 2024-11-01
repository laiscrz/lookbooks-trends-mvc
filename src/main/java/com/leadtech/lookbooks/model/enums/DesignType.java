package com.leadtech.lookbooks.model.enums;

public enum DesignType {
    LISTRADO("Listrado"),
    FLORAL("Floral"),
    XADREZ("Xadrez"),
    POA("Poá"),
    LISO("Liso"),
    ABSTRATO("Abstrato"),
    GEOMETRICO("Geométrico"),
    ANIMAL_PRINT("Animal Print"),
    ESTAMPADO("Estampado");


    private final String descricao;

    DesignType(String descricao) {
        this.descricao = descricao;
    }


    public String getDescricao() {
        return descricao;
    }
}
