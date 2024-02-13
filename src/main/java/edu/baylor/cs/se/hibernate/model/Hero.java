package edu.baylor.cs.se.hibernate.model;

import javax.persistence.*;

@Entity
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    

    @Column(nullable = false)
    private String race;

    @Column(nullable = false)
    private Float strength;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Allegiance allegiance;

    public enum Allegiance {
        GOOD, DARK
    }

    // Default constructor
    public Hero() {
    }

    // Parameterized constructor
    public Hero(String name, String race, Float strength, Allegiance allegiance) {
        this.name = name;
        this.race = race;
        this.strength = strength;
        this.allegiance = allegiance;
    }

    // Getters and setters
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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Float getStrength() {
        return strength;
    }

    public void setStrength(Float strength) {
        this.strength = strength;
    }

    public Allegiance getAllegiance() {
        return allegiance;
    }

    public void setAllegiance(Allegiance allegiance) {
        this.allegiance = allegiance;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hero hero = (Hero) o;

        return id != null ? id.equals(hero.id) : hero.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", strength=" + strength +
                ", allegiance=" + allegiance +
                '}';
    }
}
