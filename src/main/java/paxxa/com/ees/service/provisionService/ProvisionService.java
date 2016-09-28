package paxxa.com.ees.service.provisionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paxxa.com.ees.repository.provisionLevels.ProvisionLevelsRepository;

@Service
public class ProvisionService {

    @Autowired
    private ProvisionLevelsRepository provisionLevelsRepository;

}
