package data;

import business.*;
import business.enums.Role;

import java.io.*;

public class Serializer {

  public static void serialize(IDeliveryServiceProcessing deliveryServiceProcessing) {
    // Serialization
    try {
      FileOutputStream file = new FileOutputStream("file.ser");
      ObjectOutputStream out = new ObjectOutputStream(file);
      out.writeObject(deliveryServiceProcessing);
      out.close();
      file.close();
      System.out.println("Object has been serialized");
    } catch (IOException ex) {
      System.out.println("IOException is caught");
    }
  }

  public static IDeliveryServiceProcessing deserialize() {
    IDeliveryServiceProcessing deliveryService;
    try {
      FileInputStream file = new FileInputStream("file.ser");
      ObjectInputStream in = new ObjectInputStream(file);
      deliveryService = (IDeliveryServiceProcessing) in.readObject();
      System.out.println("Object has been deserialized");
      in.close();
      file.close();
      return deliveryService;
    } catch (IOException ex) {
      System.out.println("IOException is caught");
      UserService userService = new UserServiceImpl();
      userService.register(new User("admin", "admin", Role.ADMIN));
      userService.register(new User("client", "client", Role.CLIENT));
      userService.register(new User("client1", "client1", Role.CLIENT));
      userService.register(new User("client2", "client2", Role.CLIENT));
      userService.register(new User("employee", "employee", Role.EMPLOYEE));
      deliveryService = new DeliveryService(userService);
      serialize(deliveryService);
      return deliveryService;
    } catch (ClassNotFoundException ex) {
      System.out.println("ClassNotFoundException is caught");
      return new DeliveryService(new UserServiceImpl());
    }
  }
}
