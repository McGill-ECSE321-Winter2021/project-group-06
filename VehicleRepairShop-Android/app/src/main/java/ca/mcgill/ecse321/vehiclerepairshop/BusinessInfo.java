package ca.mcgill.ecse321.vehiclerepairshop;

public class
BusinessInfo {
    private String businessName;
    private String businessAddress;
    private String businessPhoneNumber;
    private String businessEmail;

    /**
     * @param businessName
     * @param businessAddress
     * @param businessPhoneNumber
     * @param businessEmail
     */
    public BusinessInfo(String businessName, String businessAddress, String businessPhoneNumber, String businessEmail) {
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessPhoneNumber = businessPhoneNumber;
        this.businessEmail = businessEmail;
    }

    /**
     * Get business name
     *
     * @return
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * get business address
     *
     * @return
     */
    public String getBusinessAddress() {
        return businessAddress;
    }

    /**
     * get business phone number
     *
     * @return
     */
    public String getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }

    /**
     * get business email
     *
     * @return
     */
    public String getBusinessEmail() {
        return businessEmail;
    }

    /**
     * set business name
     *
     * @param businessName
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * set business address
     *
     * @param businessAddress
     */
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    /**
     * set business phone number
     *
     * @param businessPhoneNumber
     */
    public void setBusinessPhoneNumber(String businessPhoneNumber) {
        this.businessPhoneNumber = businessPhoneNumber;
    }

    /**
     * set business email
     *
     * @param businessEmail
     */
    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }
}
