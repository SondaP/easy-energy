package paxxa.com.ees.service.initDb;

import com.sun.java.browser.plugin2.DOM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.entity.client.Client;
import paxxa.com.ees.entity.company.Company;
import paxxa.com.ees.entity.role.Role;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.repository.client.ClientRepository;
import paxxa.com.ees.repository.company.CompanyRepository;
import paxxa.com.ees.repository.role.RoleRepository;
import paxxa.com.ees.repository.user.UserRepository;
import paxxa.com.utils.DomainConstans;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Transactional
@Service
public class InitDbService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ClientRepository clientRepository;


    @PostConstruct
    public void init() {
        /**
         * Setting ROLES
         */
        Role roleUser = new Role();
        roleUser.setName(DomainConstans.ROLE.ROLE_USER);
        roleRepository.save(roleUser);


        Role roleAdmin = new Role();
        roleAdmin.setName(DomainConstans.ROLE.ROLE_ADMIN);
        roleRepository.save(roleAdmin);

        Role roleSuperAdmin = new Role();
        roleSuperAdmin.setName(DomainConstans.ROLE.ROLE_SUPER_ADMIN);
        roleRepository.save(roleSuperAdmin);

        /**
         * Setting USERS
         */
        User userSuperAdmin = new User();
        userSuperAdmin.setEnabled(true);
        userSuperAdmin.setName("sa");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userSuperAdmin.setPassword(encoder.encode("a"));
        userSuperAdmin.setRoles(Arrays.asList(roleSuperAdmin));
        userRepository.save(userSuperAdmin);

        User userAdmin = new User();
        userAdmin.setEnabled(true);
        userAdmin.setName("a");
        userAdmin.setPassword(encoder.encode("a"));
        userAdmin.setRoles(Arrays.asList(roleAdmin));
        userRepository.save(userAdmin);

        User user_a = new User();
        user_a.setEnabled(true);
        user_a.setName("d");
        user_a.setPassword(encoder.encode("d"));
        user_a.setRoles(Arrays.asList(roleUser));
        userRepository.save(user_a);

        /**
         * Setting COMPANIES
         */
        Company company_1 = new Company();
        company_1.setCompanyName("Savings4U");
        company_1.setNip("5641713530");
        company_1.setStreetName("Postępu");
        company_1.setStreetNumber("50");
        company_1.setZipCode("20-666");
        company_1.setCity("Warszawa");
        companyRepository.save(company_1);

        /**
         * Setting CLIENTS
         */
        Client client_1 = new Client();
        client_1.setCurrentAdvisor(user_a);
        client_1.setCompany(company_1);
        clientRepository.save(client_1);
    }
}
