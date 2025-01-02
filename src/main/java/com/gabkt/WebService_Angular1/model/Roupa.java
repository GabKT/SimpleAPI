package com.gabkt.WebService_Angular1.model;

public class Roupa {
    private int id;
    private String nome;
    private String categoria;
    private double preco;
    private String tamanho;
    private String imagem;

    public Roupa() {
    }

    public Roupa(int id, String nome, String categoria, double preco, String tamanho, String imagem) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.tamanho = tamanho;
        this.imagem = imagem;
    }

    public Roupa(String nome, String categoria, double preco, String tamanho, String imagem) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.tamanho = tamanho;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    

    @Override
    public String toString() {
        return "Roupa [nome=" + nome + ", categoria=" + categoria + ", preco=" + preco + ", tamanho=" + tamanho
                + ", imagem=" + imagem + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Roupa other = (Roupa) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
