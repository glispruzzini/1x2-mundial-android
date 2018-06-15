package it.crispybacon.mundial1x2.core.apimodels;

/**
 * Created by Jameido on 15/06/2018.
 */
public class Team {
    private String name;
    private String code;
    private String color;
    private String continent;

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
