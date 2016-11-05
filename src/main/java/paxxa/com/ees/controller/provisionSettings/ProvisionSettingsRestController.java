package paxxa.com.ees.controller.provisionSettings;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paxxa.com.domainConstans.DomainConstans;
import paxxa.com.ees.dto.provisionSettings.ProvisionVariantsProspectDTO;
import paxxa.com.ees.dto.provisionSettings.SellerForProvisionDTO;
import paxxa.com.ees.dto.provisionSettings.UserProvisionDTO;
import paxxa.com.ees.service.provisionService.ProvisionService;
import paxxa.com.ees.service.seller.SellerService;
import paxxa.com.ees.service.user.UserService;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProvisionSettingsRestController {

    final static Logger LOG = Logger.getLogger(ProvisionSettingsRestController.class);

    @Autowired
    private SellerService sellerService;
    @Autowired
    private ProvisionService provisionService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/a/provision/sellers",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SellerForProvisionDTO>> getSellersForProvision() {
        LOG.debug("Get sellers for provision");
        List<SellerForProvisionDTO> sellersForProvision = sellerService.getSellersForProvision();
        return new ResponseEntity<>(sellersForProvision, HttpStatus.OK);
    }

    @RequestMapping(value = "/a/provision/productCodes",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getProductCodes() {
        String electricity = DomainConstans.PRODUCT_CODE.ELECTRICITY;
        String gas = DomainConstans.PRODUCT_CODE.GAS;
        List<String> productCodes = Arrays.asList(electricity, gas);
        return new ResponseEntity<>(productCodes, HttpStatus.OK);
    }


    @RequestMapping(value = "/a/provision/provisionVariants", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<UserProvisionDTO> getProvisionVariants(@RequestBody ProvisionVariantsProspectDTO
                                                                         provisionVariantsProspectDTO,
                                                                 Principal principal) {
        if (!userService.hasUserExpectedRole(principal.getName(), DomainConstans.ROLE.ROLE_ADMIN)) {
            return null;
        }

        String userName = provisionVariantsProspectDTO.getUserName();
        String product_code = provisionVariantsProspectDTO.getProductCode();
        String seller_code = provisionVariantsProspectDTO.getSellerCode();
        UserProvisionDTO userProvisionDTO = provisionService.getUserProvisionDTO(userName, product_code, seller_code);
        return new ResponseEntity<>(userProvisionDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/a/provision/userProvision", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<UserProvisionDTO> updateUserProvisionVariants(@RequestBody UserProvisionDTO userProvisionDTO) {

        provisionService.updateUserProvisionVariants(userProvisionDTO);

        String userName = userProvisionDTO.getUserName();
        String product_code = userProvisionDTO.getProductCode();
        String seller_code = userProvisionDTO.getSellerCode();
        UserProvisionDTO userProvisionDTOAfterUpdate = provisionService.getUserProvisionDTO(userName, product_code, seller_code);

        return new ResponseEntity<>(userProvisionDTOAfterUpdate, HttpStatus.OK);
    }


}
