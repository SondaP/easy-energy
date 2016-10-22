package paxxa.com.domainConstans;

public class DomainConstans {

    public interface ROLE {
        String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
        String ROLE_ADMIN = "ROLE_ADMIN";
        String ROLE_TRADER = "ROLE_TRADER";
    }

    public interface SELLER_CODE {
        String CEZ_SELLER = "CEZ";
        String TAURON_SELLER = "TAURON";
    }

    public interface PRODUCT_CODE {
        String ELECTRICITY = "Energia elektryczna";
        String GAS = "Gas";
    }

    public interface HTTP_HEADERS_EXCEPTION_CODE {
        String MISSING_DATA_EXCEPTION = "Missing-Data-Exception";
    }


}
