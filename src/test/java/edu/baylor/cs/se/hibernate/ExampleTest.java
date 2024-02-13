package edu.baylor.cs.se.hibernate;

import edu.baylor.cs.se.hibernate.model.Hero;
import edu.baylor.cs.se.hibernate.model.Hero.Allegiance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExampleTest {

    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setUp() {
    }

    @Test
    public void testHeroCreationWithUniqueName() {
        Hero hero = new Hero("Bilbo", "Hobbit", 50f, Allegiance.GOOD);
        entityManager.persistAndFlush(hero);
        Hero found = entityManager.find(Hero.class, hero.getId());
        assertThat(found.getName()).isEqualTo(hero.getName());
    }

   @Test
public void testHeroCreationFailsWithDuplicateName() {
    Hero hero1 = new Hero("Bilbo", "Hobbit", 50f, Allegiance.GOOD);
    entityManager.persistAndFlush(hero1);
    try {
        Hero hero2 = new Hero("Bilbo", "Elf", 60f, Allegiance.DARK);
        entityManager.persistAndFlush(hero2);
        fail("PersistenceException expected due to duplicate hero name");
    } catch (PersistenceException expectedException) {
        
    }
}

@Test
public void testListingHeroesOrderedByName() {
    List<Hero> heroes = entityManager.getEntityManager()
        .createQuery("SELECT h FROM Hero h ORDER BY h.name", Hero.class)
        .getResultList();
    assertThat(heroes).isNotEmpty();
}

@Test
public void testSearchHeroesByName() {
    Hero hero1 = new Hero("Aragorn", "Human", 95f, Allegiance.GOOD);
    Hero hero2 = new Hero("Legolas", "Elf", 90f, Allegiance.GOOD);
    Hero hero3 = new Hero("Gimli", "Dwarf", 85f, Allegiance.GOOD);
    entityManager.persistAndFlush(hero1);
    entityManager.persistAndFlush(hero2);
    entityManager.persistAndFlush(hero3);

    List<Hero> searchResults = entityManager.getEntityManager()
        .createQuery("SELECT h FROM Hero h WHERE h.name LIKE :name", Hero.class)
        .setParameter("name", "%gol%")
        .getResultList();

    assertThat(searchResults).hasSize(1);
    assertThat(searchResults.get(0).getName()).isEqualTo("Legolas");
}

    

}
