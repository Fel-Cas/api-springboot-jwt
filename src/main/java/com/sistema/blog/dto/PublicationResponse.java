package com.sistema.blog.dto;

import java.util.List;

public class PublicationResponse {
    private List<PublicationDTO> contenido;
    private int numberPage;
    private int sizePage;
    private Long totalElements;
    private int totalPages;
    private  boolean lastOne;

    public PublicationResponse() {
    }

    public List<PublicationDTO> getContenido() {
        return contenido;
    }

    public PublicationResponse setContenido(List<PublicationDTO> contenido) {
        this.contenido = contenido;
        return this;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public PublicationResponse setNumberPage(int numberPage) {
        this.numberPage = numberPage;
        return this;
    }

    public int getSizePage() {
        return sizePage;
    }

    public PublicationResponse setSizePage(int sizePage) {
        this.sizePage = sizePage;
        return this;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public PublicationResponse setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public PublicationResponse setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public boolean isLastOne() {
        return lastOne;
    }

    public PublicationResponse setLastOne(boolean lastOne) {
        this.lastOne = lastOne;
        return this;
    }
}
