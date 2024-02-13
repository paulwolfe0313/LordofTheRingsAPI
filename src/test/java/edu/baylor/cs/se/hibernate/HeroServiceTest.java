package edu.baylor.cs.se.hibernate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import edu.baylor.cs.se.hibernate.model.Hero;
import edu.baylor.cs.se.hibernate.model.Hero.Allegiance;
import edu.baylor.cs.se.hibernate.services.HeroRepository;
import edu.baylor.cs.se.hibernate.services.HeroService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;

    @InjectMocks
    private HeroService heroService;

    @Test
    public void testFightSameSide() {
        Hero hero1 = new Hero("Hero1", "Race1", 100f, Allegiance.GOOD);
        Hero hero2 = new Hero("Hero2", "Race2", 90f, Allegiance.GOOD);
        // Assuming simulateFight is observable through the fight method
        when(heroRepository.findHeroById(any(Long.class))).thenReturn(Optional.of(hero1), Optional.of(hero2));
        Hero result = heroService.fight(1L, 2L);
        Assert.assertNull("Heroes on the same side should not fight", result);
    }

    @Test
    public void testFightAnotherSide() {
        Hero hero1 = new Hero("Hero1", "Race1", 100f, Allegiance.GOOD);
        Hero hero2 = new Hero("Hero2", "Race2", 80f, Allegiance.DARK);
        when(heroRepository.findHeroById(any(Long.class))).thenReturn(Optional.of(hero1), Optional.of(hero2));
        Hero result = heroService.fight(1L, 2L);
        Assert.assertEquals("Hero with greater strength should win", hero1, result);
    }

    @Test
    public void testTragedyFight() {
        Hero hero1 = new Hero("Hero1", "Race1", 100f, Allegiance.GOOD);
        Hero hero2 = new Hero("Hero2", "Race2", 100f, Allegiance.DARK);
        when(heroRepository.findHeroById(any(Long.class))).thenReturn(Optional.of(hero1), Optional.of(hero2));
        Hero result = heroService.fight(1L, 2L);
        Assert.assertNull("In a tragedy fight, there should be no winner", result);
    }
}

