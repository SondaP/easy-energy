package paxxa.com.ees.service.initDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paxxa.com.ees.dto.offer.electricityOffer.offer.ElectricityOfferRoot;
import paxxa.com.ees.entity.Provision.Provision;
import paxxa.com.ees.entity.Provision.ProvisionVariant;
import paxxa.com.ees.entity.client.Client;
import paxxa.com.ees.entity.company.Company;
import paxxa.com.ees.entity.offerStorage.OfferStorage;
import paxxa.com.ees.entity.personalData.PersonalData;
import paxxa.com.ees.entity.role.Role;
import paxxa.com.ees.entity.seller.Seller;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.repository.client.ClientRepository;
import paxxa.com.ees.repository.company.CompanyRepository;
import paxxa.com.ees.repository.personalData.PersonalDataRepository;
import paxxa.com.ees.repository.provision.ProvisionRepository;
import paxxa.com.ees.repository.provision.ProvisionVariantRepository;
import paxxa.com.ees.repository.role.RoleRepository;
import paxxa.com.ees.repository.seller.SellerRepository;
import paxxa.com.ees.repository.user.UserRepository;
import paxxa.com.domainConstans.DomainConstans;
import paxxa.com.ees.service.offerStorage.OfferStorageService;
import paxxa.com.ees.service.utils.SampleDataService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
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
    @Autowired
    private PersonalDataRepository personalDataRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private SampleDataService sampleDataService;
    @Autowired
    private OfferStorageService offerStorageService;
    @Autowired
    private ProvisionRepository provisionRepository;
    @Autowired
    private ProvisionVariantRepository provisionVariantRepository;


    @PostConstruct
    public void init() {
        /**
         * Setting ROLES
         */
        Role roleUser = new Role();
        roleUser.setName(DomainConstans.ROLE.ROLE_TRADER);
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
        userSuperAdmin.setEmail("superAdmin@gmial.com");
        userSuperAdmin.setRoles(Arrays.asList(roleSuperAdmin));
        userRepository.save(userSuperAdmin);

        PersonalData personalDataAdmin = new PersonalData();
        personalDataAdmin.setFirstName("Paxxa");
        personalDataAdmin.setSurname("Paxxa");
        personalDataAdmin.setEmail("paxxa.kontakt@gmail.com");
        personalDataAdmin.setPhoneNumber("792600330");
        personalDataRepository.save(personalDataAdmin);

        User paxxa_user1 = new User();
        paxxa_user1.setEnabled(true);
        paxxa_user1.setName("d");
        paxxa_user1.setPassword(encoder.encode("d"));
        paxxa_user1.setEmail("handlowiec_paxxa@gmail.com");
        paxxa_user1.setRoles(Arrays.asList(roleUser));
        User paxxa = userRepository.save(paxxa_user1);


        User userAdmin_Paxxa = new User();
        userAdmin_Paxxa.setEnabled(true);
        userAdmin_Paxxa.setName("paxxa");
        userAdmin_Paxxa.setPassword(encoder.encode("a"));
        userAdmin_Paxxa.setEmail("paxxa.kontakt@gmail.com");
        userAdmin_Paxxa.setPersonalData(personalDataAdmin);
        userAdmin_Paxxa.setRoles(Arrays.asList(roleAdmin));
        userAdmin_Paxxa.setUserList(Arrays.asList(paxxa_user1));
        userRepository.save(userAdmin_Paxxa);


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
        client_1.setCurrentAdvisor(paxxa_user1);
        client_1.setCompany(company_1);
        clientRepository.save(client_1);


        /**
         * Setting SELLER
         */
        Seller seller_1 = new Seller();
        seller_1.setName("TAURON");
        seller_1.setEnabled(true);
        sellerRepository.save(seller_1);


        /**
         * Setting electricity offer to Paxxa
         */
        ElectricityOfferRoot electricityRootOfferDTO = sampleDataService.createElectricityRootOfferDTO();
        OfferStorage savedOfferStorage = offerStorageService.createOrUpdateOffer(electricityRootOfferDTO, userAdmin_Paxxa.getName());

        /**
         * Setting Provision levels for Paxxa
         */
        ProvisionVariant provisionVariant_CEZ_1 = new ProvisionVariant();
        provisionVariant_CEZ_1.setProvisionLevelCode("Próg I");
        provisionVariant_CEZ_1.setProvisionPercentageValue(new BigDecimal(0.65));

        provisionVariantRepository.save(provisionVariant_CEZ_1);

        ProvisionVariant provisionVariant_CEZ_2 = new ProvisionVariant();
        provisionVariant_CEZ_2.setProvisionLevelCode("Próg II");
        provisionVariant_CEZ_2.setProvisionPercentageValue(new BigDecimal(0.75));

        provisionVariantRepository.save(provisionVariant_CEZ_2);

        Provision provision = new Provision();
        provision.setSellerCode(DomainConstans.SELLER_CODE.CEZ_SELLER);
        provision.setUser(userAdmin_Paxxa);
        provision.setProvisionVariantList(Arrays.asList(provisionVariant_CEZ_1, provisionVariant_CEZ_2));
        provisionRepository.save(provision);

    }

}
