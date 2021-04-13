package ca.mcgill.ecse321.vehiclerepairshop;

// This contains the current username
// To call it: String s = SingletonClass.getInstance().getCurrentUsername();
public class SingletonClass {

    private static SingletonClass instance;
    private String currentUsername;

    public static SingletonClass getInstance() {
        if (instance == null)
            instance = new SingletonClass();
        return instance;
    }

    private SingletonClass() {
    }

    public String getCurrentUsername() {
        return this.currentUsername;
    }

    public void setCurrentUsername(String value) {
        this.currentUsername = value;
    }
}

