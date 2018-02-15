import tinyb.BluetoothDevice;
import tinyb.BluetoothManager;
import java.util.ArrayList;
import java.util.List;


import static java.lang.Thread.sleep;

/**
 * This is a simple class meant to Demo some some basic information
 * Getting wiht regards to Devices, and basic discovery
 * Nothing very complicated
 * Created by scherban on 2/15/18.
 */
public class simpleFind {


    /**
     * This is just to get the basic info
     * @param device - the bluetooth device who's info you would like
     * @return A string of the info
     */
    private static String getBasicDeviceInfo(BluetoothDevice device){
        String returnVal = "Address : " + device.getAddress();
        returnVal += "\nName : " + device.getName();
        returnVal += "\nConnected : " + device.getConnected();
        return returnVal;
    }


    /**
     * A demonstration of the main string parameters you can
     * get from the device class
     * @param device - the bluetooth device who's info you would like
     * @return A string with the info
     */
    private static String getAllDeviceInfo(BluetoothDevice device){
        String returnVal = getBasicDeviceInfo(device);
        returnVal += "\nAlias : " + device.getAlias();
        returnVal += "\nModalias : " + device.getModalias();
        returnVal += "\nIcon : " + device.getIcon();
        return returnVal;
    }

    /**
     * Get a list of the info for all devices
     * This method starts discovery and then
     * uses the above methods to get all of the info
     * for each of them
     * @return A list of all of the info
     */
    private static List<String> listAllDevices(){


        /*
         * This is a very important step. This starts-up the interaction with
         * the tinyB Library through the BluetoothManager object
         *
         * There can only be one BluetoothManager at a time.
         * the reference to this object is gotten from the
         * getBluetoothManager() method.
         *
         */
        BluetoothManager manager = BluetoothManager.getBluetoothManager();

        // Begins Bluetooth discovery
        boolean startedDiscovery = manager.startDiscovery();


        //if discovery fails, simply return null
        if(!startedDiscovery){
            return null;
        }


        //a small break, will be put to better use later
        try {
            sleep(400);
        }catch(InterruptedException e){
            System.err.print("Something went wrong with sleep");
            return null;
        }


        //Uses the getDevices() instance method to get a list
        //of Devices
        List<BluetoothDevice> list = manager.getDevices();

        //creates an empty array list to hold all the info
        List<String> informationList = new ArrayList<>();


        //Iterate through the devices list and add the
        //info to the iformationlist
        for(BluetoothDevice device : list){
            informationList.add(getAllDeviceInfo(device));
        }

        manager.stopDiscovery();

        return informationList;

    }


    public static void main(String[] args){
        List<String> info = listAllDevices();

        //do a null check, exit program safely
        if(info == null){
            return;
        }

        //print everythign you want
        for(String data:info){
            System.out.println(data + "\n");
        }


    }



}
