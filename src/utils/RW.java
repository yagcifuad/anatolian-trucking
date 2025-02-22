package utils;

import fxControllers.MainPage;
import model.Driver;
import model.Manager;
import model.User;

import java.io.*;
import java.util.List;

public class RW {
    private static final String fileNameDrivers = "driverList.txt";
    private static final String fileNameManagers = "managerList.txt";
    public static void writeToFile(List<User> drivers, List<User> managers) {
        try {
            if (drivers != null){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileNameDrivers));
                objectOutputStream.writeObject(drivers);
                objectOutputStream.close();}
           else{ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileNameManagers));
                objectOutputStream.writeObject(managers);
                objectOutputStream.close();}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<User> readFromFile(String type) {
        List<User> users = null;
        try {
            String fileName =type.equals("D")? fileNameDrivers: fileNameManagers;
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            users =(List<User>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        return users;
    }

}
