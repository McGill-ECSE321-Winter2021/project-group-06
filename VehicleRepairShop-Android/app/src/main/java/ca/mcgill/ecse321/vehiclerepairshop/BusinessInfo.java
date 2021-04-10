package ca.mcgill.ecse321.vehiclerepairshop;

public class
BusinessInfo {
    private String businessName;
    private String businessAddress;
    private String businessPhoneNumber;
    private String businessEmail;


    public BusinessInfo(String businessName, String businessAddress, String businessPhoneNumber, String businessEmail) {
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessPhoneNumber = businessPhoneNumber;
        this.businessEmail = businessEmail;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public String getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public void setBusinessPhoneNumber(String businessPhoneNumber) {
        this.businessPhoneNumber = businessPhoneNumber;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }
}
