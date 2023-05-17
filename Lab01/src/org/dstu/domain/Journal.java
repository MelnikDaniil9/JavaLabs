package org.dstu.domain;

public class Journal extends Publication {
    private int issueNumber;
    private String editor;
    private String topic;

    public Journal() {
    }

    public Journal(String[] line) {
        super(line[1], line[2], Integer.parseInt(line[3]));
        issueNumber = Integer.parseInt(line[4]);
        editor = line[5];
        topic = line[6];
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String degree) {
        this.editor = editor;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(int stage) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Журнал: ");
        builder.append(super.toString());
        builder.append(" | Номер выпуска: ");
        builder.append(issueNumber);
        builder.append(" | Имя редактора: ");
        builder.append(editor);
        builder.append(" | Тема выпуска: ");
        builder.append(topic);
        return builder.toString();
    }
}
