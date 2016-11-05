package paxxa.com.ees.dto.provisionSettings;

import java.util.List;

public class UserProvisionDTO {

    private String userName;
    private String productCode;
    private String sellerCode;
    private List<ProvisionVariantDTO> provisionVariantDTOList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public List<ProvisionVariantDTO> getProvisionVariantDTOList() {
        return provisionVariantDTOList;
    }

    public void setProvisionVariantDTOList(List<ProvisionVariantDTO> provisionVariantDTOList) {
        this.provisionVariantDTOList = provisionVariantDTOList;
    }
}
