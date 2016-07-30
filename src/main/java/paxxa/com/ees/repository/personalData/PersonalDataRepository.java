package paxxa.com.ees.repository.personalData;

import org.springframework.data.jpa.repository.JpaRepository;
import paxxa.com.ees.entity.personalData.PersonalData;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
}
