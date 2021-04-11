package ca.mcgill.ecse321.vehiclerepairshop;

public class Appointment {

        private String startTime;
        private String endTime;
        private String startDate;
        private String endDate;
        private String service;

        public Appointment(String startTime, String endTime,  String startDate, String endDate, String service) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.startDate = startDate;
            this.endDate = endDate;
            this.service = service;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

         public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public void setService(String service){
            this.service = service;
        }

        public String getService() {
            return service;
        }
}
