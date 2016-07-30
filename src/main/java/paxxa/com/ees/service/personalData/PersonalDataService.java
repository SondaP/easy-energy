package paxxa.com.ees.service.personalData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.dto.PersonalDataDTO;
import paxxa.com.ees.entity.personalData.PersonalData;
import paxxa.com.ees.repository.personalData.PersonalDataRepository;

@Transactional
@Service
public class PersonalDataService {

    @Autowired
    private PersonalDataRepository personalDataRepository;

    public void updatePersonalData(PersonalDataDTO personalDataDTO){
        PersonalData personalDataEntity = personalDataRepository.getOne(personalDataDTO.getId());
        personalDataEntity.setId(personalDataDTO.getId());
        personalDataEntity.setFirstName(personalDataDTO.getFirstName());
        personalDataEntity.setSurname(personalDataDTO.getSurname());
        personalDataEntity.setEmail(personalDataDTO.getEmail());
        personalDataEntity.setPhoneNumber(personalDataDTO.getPhoneNumber());
        personalDataRepository.saveAndFlush(personalDataEntity);
    }
}
