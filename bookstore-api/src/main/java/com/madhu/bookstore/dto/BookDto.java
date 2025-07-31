package com.madhu.bookstore.dto;

import jakarta.validation.constraints.NotNull;

public class BookDto {
    private String title;
    private double price;

    @NotNull(message = "Author ID is required")
    private Long authorId;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Long getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}