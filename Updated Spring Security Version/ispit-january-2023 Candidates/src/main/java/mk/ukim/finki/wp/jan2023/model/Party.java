package mk.ukim.finki.wp.jan2023.model;

public class Party {

    public Party() {
    }

    public Party(String name) {
        this.name = name;
    }

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
