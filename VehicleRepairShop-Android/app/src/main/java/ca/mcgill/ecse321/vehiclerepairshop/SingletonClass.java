package ca.mcgill.ecse321.vehiclerepairshop;

// This contains the current username, call it: String s = SingletonClass.getInstance().getCurrentUsername();
public class SingletonClass {

    private static SingletonClass instance;

    public static SingletonClass getInstance() {
        if (instance == null)
            instance = new SingletonClass();
        return instance;
    }

    private SingletonClass() {
    }

    private String currentUsername;

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String value) {
        this.currentUsername = value;
    }
}

