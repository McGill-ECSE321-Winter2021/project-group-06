package ca.mcgill.ecse321.vehiclerepairshop;

public class OfferedService {
    private String offeredServiceId;
    private String offeredServiceName;
    private int offeredServiceDuration;
    private double offeredServicePrice;

    /**
     * @param offeredServiceId
     * @param offeredServiceName
     * @param offeredServiceDuration
     * @param offeredServicePrice
     */
    public OfferedService(String offeredServiceId, String offeredServiceName, int offeredServiceDuration, double offeredServicePrice) {
        this.offeredServiceId = offeredServiceId;
        this.offeredServiceName = offeredServiceName;
        this.offeredServiceDuration = offeredServiceDuration;
        this.offeredServicePrice = offeredServicePrice;
    }

    /**
     * get offered service id
     *
     * @return
     */
    public String getOfferedServiceId() {
        return offeredServiceId;
    }

    /**
     * get offered service name
     *
     * @return
     */
    public String getOfferedServiceName() {
        return offeredServiceName;
    }

    /**
     * get offered service duration
     *
     * @return
     */
    public int getOfferedServiceDuration() {
        return offeredServiceDuration;
    }

    /**
     * get offered service price
     *
     * @return
     */
    public double getOfferedServicePrice() {
        return offeredServicePrice;
    }

    /**
     * set offered service id
     *
     * @param offeredServiceId
     */
    public void setOfferedServiceId(String offeredServiceId) {
        this.offeredServiceId = offeredServiceId;
    }

    /**
     * set offered service name
     *
     * @param offeredServiceName
     */
    public void setOfferedServiceName(String offeredServiceName) {
        this.offeredServiceName = offeredServiceName;
    }

    /**
     * set offered service duration
     *
     * @param offeredServiceDuration
     */
    public void setOfferedServiceDuration(int offeredServiceDuration) {
        this.offeredServiceDuration = offeredServiceDuration;
    }

    /**
     * set offered service price
     *
     * @param offeredServicePrice
     */
    public void setOfferedServicePrice(double offeredServicePrice) {
        this.offeredServicePrice = offeredServicePrice;
    }


}