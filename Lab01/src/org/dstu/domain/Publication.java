package org.dstu.domain;

public abstract class Publication implements IUniversityPublication {
    private String title;
    private String author;
    private int year;

    public Publication(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Publication(String[] line) {
        title = line[1];
        author = line[2];
        year = Integer.parseInt(line[3]);
    }

    public Publication() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Название: ");
        builder.append(title);
        builder.append(" | Автор: ");
        builder.append(author);
        builder.append(" | Год выпуска: ");
        builder.append(year);
        return builder.toString();
    }
}