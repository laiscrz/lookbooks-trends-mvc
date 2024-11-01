package com.leadtech.lookbooks.model.enums;

public enum CategoryType {

    CAMISETA("Camiseta"),
    CALCA("Calça"),
    VESTIDO("Vestido"),
    JAQUETA("Jaqueta"),
    SAIA("Saia"),
    SHORTS("Shorts"),
    SUETER("Suéter"),
    CAMISA("Camisa"),
    JAQUETA_CASACO("Jaqueta Casaco"),
    CONJUNTO("Conjunto");

    private final String descricao;

    CategoryType(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
