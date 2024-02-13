package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    @Query("SELECT h FROM Hero h WHERE h.id = ?1")
    Optional<Hero> findHeroById(Long id);
    
    List<Hero> findAllByOrderByNameAsc();

   
    List<Hero> findByNameContaining(String name);
    
}
