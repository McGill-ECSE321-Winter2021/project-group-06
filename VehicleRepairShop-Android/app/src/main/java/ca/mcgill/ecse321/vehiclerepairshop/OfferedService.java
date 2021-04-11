package ca.mcgill.ecse321.vehiclerepairshop;

public class OfferedService {
    private String offeredServiceId;
    private String offeredServiceName;
    private int offeredServiceDuration;
    private double offeredServicePrice;

    public OfferedService(String offeredServiceId, String offeredServiceName, int offeredServiceDuration, double offeredServicePrice) {
        this.offeredServiceId = offeredServiceId;
        this.offeredServiceName = offeredServiceName;
        this.offeredServiceDuration = offeredServiceDuration;
        this.offeredServicePrice = offeredServicePrice;
    }

    public String getOfferedServiceId() {
        return offeredServiceId;
    }

    public String getOfferedServiceName() {
        return offeredServiceName;
    }

    public int getOfferedServiceDuration() {
        return offeredServiceDuration;
    }

    public double getOfferedServicePrice() {
        return offeredServicePrice;
    }

    public void setOfferedServiceId(String offeredServiceId) {
        this.offeredServiceId = offeredServiceId;
    }

    public void setOfferedServiceName(String offeredServiceName) {
        this.offeredServiceName = offeredServiceName;
    }

    public void setOfferedServiceDuration(int offeredServiceDuration) {
        this.offeredServiceDuration = offeredServiceDuration;
    }

    public void setOfferedServicePrice(double offeredServicePrice) {
        this.offeredServicePrice = offeredServicePrice;
    }





}