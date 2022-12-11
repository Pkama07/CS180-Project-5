import java.util.ArrayList;
import java.io.*;

/**
 * User
 *
 * Class that extends User that holds all the operations that Seller objects can do
 * @author Adrienne Peters, Gunwoo Kang, BLK
 *
 * @version Nov 12, 2022
 *
 */
public class User implements java.io.Serializable {
    private String userName; //username of the user’s account
    private String passWord; //password for the user’s account
    private String email; //email associated with the user’s account
    private static ArrayList<Store> store = new ArrayList<Store>(); //ArrayList of all customer accounts
    private static ArrayList<Customer> customers = new ArrayList<Customer>(); //ArrayList of all customer accounts

    //Constructors
    public User(String userName , String passWord , String email) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }

    public User() {}

    public static ArrayList<Store> getStore() { //Returns static store ArrayList
        return store;
    }
    public static ArrayList<Customer> getCustomers() { //Returns static customer ArrayList
        return customers;
    }
    public String getUserName() { //Returns a User object’s userName
        return userName;
    }
    public String getPassWord() { //Returns a User object’s  passWord
        return passWord;
    }
    public String getEmail() { //Returns a User object’s email
        return email;
    }
    public void setUserName(String userName) { //Sets a User object’s userName
        this.userName = userName;
    }
    public void setPassWord(String passWord) { //Sets a User object’s passWord
        this.passWord = passWord;
    }
    public void setEmail(String email) { //Sets a User object’s email
        this.email = email;
    }

    public boolean removeCustomer(Customer customer) {
        return customers.remove(customer);
    }

    public boolean addStore(Store newStore) {
        return store.add(newStore);
    }

    public boolean removeStore(Store remove) {
        return store.remove(remove);
    }

    public void removeSeller(Seller seller) {
        for (Store store: store) {
            if (store.getSeller().equals(seller)) {
                removeStore(store);
            }
        }
    }
    
    //Returns a Store object from the static Store ArrayList that matches the storeName parameter
    public Store findStore(String storeName) {
        //Index starts out at -1
        int index = -1;
        //Looping through store ArrayList
        for ( int i = 0; i < store.size(); i++ ) {
            //If the store name in the list equals the storeName parameter
            if ( store.get(i).getStoreName().equals(storeName) ) {
                //Setting index to the index it is in the ArrayList
                index = i;
                break;
            }
        }
        //If the index has not been reset, then return null
        if ( index == -1 ) {
            return null;
        }
        //Return Store object at the index
        return store.get(index);
    }
    //Returns a Customer object from the static Customer ArrayList that has the same username and password as
    // the parameters
    public static Customer findCustomer(String userName , String passWord) {
        //Index starts out at -1
        int index = -1;
        //Looping through customers ArrayList
        for ( int i = 0; i < customers.size(); i++) {
            //If the customer's userName and passWord matches the parameters
            if ( customers.get(i).getUserName().equals(userName) && customers.get(i).getPassWord().equals(passWord) ) {
                //Setting index to the index it is in the ArrayList
                index = i;
                break;
            }
        }
        //If the index has not been reset, then return null
        if ( index == -1 ) {
            return null;
        }
        //Return Store object at the index
        return customers.get(index);
    }
}