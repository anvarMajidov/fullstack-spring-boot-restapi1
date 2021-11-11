package myapp.demo.models;

public class Blog {
    private int id;
    private String header;
    private String body;
    private String description;

    public Blog(){}

    public Blog(String header, String body, String description) {
        this.header = header;
        this.body = body;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
