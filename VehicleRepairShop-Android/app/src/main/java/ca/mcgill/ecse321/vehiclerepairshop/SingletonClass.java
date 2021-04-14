package ca.mcgill.ecse321.vehiclerepairshop;

// This contains the current username, call it: String s = SingletonClass.getInstance().getCurrentUsername();
public class SingletonClass {

    private static SingletonClass instance;
    private String currentUsername;

    /**
     * get global instance
     *
     * @return
     */
    public static SingletonClass getInstance() {
        if (instance == null)
            instance = new SingletonClass();
        return instance;
    }

    private SingletonClass() {
    }

    /**
     * get global current username
     *
     * @return
     */
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    /**
     * set global current username
     *
     * @param value
     */
    public void setCurrentUsername(String value) {
        this.currentUsername = value;
    }
}

