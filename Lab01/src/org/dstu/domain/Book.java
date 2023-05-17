package org.dstu.domain;

public class Book extends Publication {
    private String genre;
    private String isbn;
    private String language;

    public Book() {
    }

    public Book(String[] line) {
        super(line[1], line[2], Integer.parseInt(line[3]));
        genre = line[4];
        isbn = line[5];
        language = line[6];
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Книга: ");
        builder.append(super.toString());
        builder.append(" | Жанр: ");
        builder.append(genre);
        builder.append(" | isbn: ");
        builder.append(isbn);
        builder.append(" | язык: ");
        builder.append(language);
        return builder.toString();
    }
}
