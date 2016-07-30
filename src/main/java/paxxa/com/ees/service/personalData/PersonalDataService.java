package paxxa.com.ees.service.personalData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.dto.PersonalDataDTO;
import paxxa.com.ees.entity.personalData.PersonalData;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.repository.personalData.PersonalDataRepository;
import paxxa.com.ees.repository.user.UserRepository;

@Transactional
@Service
public class PersonalDataService {

    @Autowired
    private PersonalDataRepository personalDataRepository;
    @Autowired
    private UserRepository userRepository;

    public void addOrUpdatePersonalData(PersonalDataDTO personalDataDTO, String userName) {
        PersonalData personalData = mapToPersonalData(personalDataDTO);
        if (checkIfAnyPersonalDataExists(personalDataDTO)) {
            personalDataRepository.save(personalData);
        } else {
            personalDataRepository.save(personalData);
            User userEntity = userRepository.findByName(userName);
            userEntity.setPersonalData(personalData);
            userRepository.save(userEntity);
        }

    }

    private boolean checkIfAnyPersonalDataExists(PersonalDataDTO personalDataDTO) {
        if (personalDataDTO.getId() == null) {
            return false;
        }
        return true;
    }

    private PersonalData mapToPersonalData(PersonalDataDTO personalDataDTO) {
        PersonalData personalDataEntity = new PersonalData();
        personalDataEntity.setId(personalDataDTO.getId());
        personalDataEntity.setFirstName(personalDataDTO.getFirstName());
        personalDataEntity.setSurname(personalDataDTO.getSurname());
        personalDataEntity.setEmail(personalDataDTO.getEmail());
        personalDataEntity.setPhoneNumber(personalDataDTO.getPhoneNumber());
        return personalDataEntity;
    }
}
