package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.model.Hero;
import edu.baylor.cs.se.hibernate.services.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private final HeroRepository heroRepository;

    @Autowired
    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Transactional
    public Hero createHero(Hero hero) {
        return heroRepository.save(hero);
    }

    public List<Hero> getAllHeroesOrderedByName() {
        return heroRepository.findAllByOrderByNameAsc();
    }

    public Hero getHeroById(Long id) {
        return heroRepository.findHeroById(id).orElse(null);
    }

    public List<Hero> searchHeroesByName(String name) {
        return heroRepository.findByNameContaining(name);
    }

    @Transactional
    public Hero fight(Long id1, Long id2) {
        Optional<Hero> optionalHero1 = heroRepository.findHeroById(id1);
        Optional<Hero> optionalHero2 = heroRepository.findHeroById(id2);

        if (!optionalHero1.isPresent() || !optionalHero2.isPresent()) {
            return null; // One of the heroes doesn't exist
        }

        Hero hero1 = optionalHero1.get();
        Hero hero2 = optionalHero2.get();

        // Logic to simulate the fight and determine the winner
        // Assuming we have a method called `simulateFight(Hero hero1, Hero hero2)` that returns the winner
        Hero winner = simulateFight(hero1, hero2);

        // Remove the defeated hero from the database
        if (winner != null) {
            Hero defeated = (winner.equals(hero1)) ? hero2 : hero1;
            heroRepository.delete(defeated);
        }

        return winner;
    }

    private Hero simulateFight(Hero hero1, Hero hero2) {
       
        if (!hero1.getAllegiance().equals(hero2.getAllegiance())) {
            return hero1.getStrength() > hero2.getStrength() ? hero1 : hero2;
        } else {
            
            return null;
        }
    }
}
