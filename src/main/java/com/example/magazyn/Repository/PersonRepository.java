package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long>
{
    Optional<Person> findPersonByPesel(String pesel);
}
