package com.cresol.eventsTest.repository;

import com.cresol.eventsTest.domain.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

}
