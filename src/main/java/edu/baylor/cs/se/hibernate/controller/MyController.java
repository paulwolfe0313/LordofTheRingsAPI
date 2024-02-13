package edu.baylor.cs.se.hibernate.controller;

import edu.baylor.cs.se.hibernate.model.Hero;
import edu.baylor.cs.se.hibernate.services.HeroService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/heroes")
public class MyController {

    private final HeroService heroService;

    @Autowired
    public MyController(HeroService heroService) {
        this.heroService = heroService;
    }

    @PostMapping("/populate")
    public ResponseEntity<String> populateHeroes() {
        List<Hero> sampleHeroes = Arrays.asList(
        new Hero("Frodo", "Hobbit", 10f, Hero.Allegiance.GOOD),
        new Hero("Aragorn", "Human", 95f, Hero.Allegiance.GOOD),
        new Hero("Legolas", "Elf", 90f, Hero.Allegiance.GOOD),
        new Hero("Gimli", "Dwarf", 85f, Hero.Allegiance.GOOD),
        new Hero("Saruman", "Wizard", 100f, Hero.Allegiance.DARK),
        new Hero("Sauron", "Maia", 100f, Hero.Allegiance.DARK),
        new Hero("Witch-king of Angmar", "Human", 95f, Hero.Allegiance.DARK),
        new Hero("Gollum", "Hobbit", 30f, Hero.Allegiance.DARK),
        new Hero("Grima Wormtongue", "Human", 40f, Hero.Allegiance.DARK)

    );

    sampleHeroes.forEach(hero -> heroService.createHero(hero));

    return ResponseEntity.ok("Database populated with heroes");
    }

   @PostMapping
public ResponseEntity<List<Hero>> createHeroes(@RequestBody List<Hero> heroes) {
    List<Hero> savedHeroes = heroes.stream()
                                   .map(heroService::createHero)
                                   .collect(Collectors.toList());
    return ResponseEntity.ok(savedHeroes);
}
    @GetMapping
    public List<Hero> getAllHeroes() {
        return heroService.getAllHeroesOrderedByName();
    }

    @GetMapping("/{id}")
public Hero getHeroById(@PathVariable Long id) {
    return heroService.getHeroById(id);
}

    @GetMapping(params = "name")
    public List<Hero> searchHeroesByName(@RequestParam String name) {
        return heroService.searchHeroesByName(name);
    }

    @PostMapping("/fight")
    public Hero fight(@RequestParam Long id1, @RequestParam Long id2) {
        return heroService.fight(id1, id2);
    }
}
