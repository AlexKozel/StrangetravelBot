package com.example.StrangeTravelBot.repository;

import com.example.StrangeTravelBot.entity.CapitalsInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitalRepository extends CrudRepository<CapitalsInfo, Integer> {
}
