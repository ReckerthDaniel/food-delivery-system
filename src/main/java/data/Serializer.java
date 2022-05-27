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
      // reset the static variables for id as they were not serialized
      if(deliveryService.getMenu() != null) {
        Utils.menuItemId = deliveryService.getMenu().size();
      } else {
        Utils.menuItemId = 0;
      }

      if(deliveryService.getOrders() != null) {
        Utils.orderId = deliveryService.getOrders().size();
      } else {
        Utils.orderId = 0;
      }

      if(deliveryService.getUserService().getUsers() != null) {
        Utils.userId = deliveryService.getUserService().getUsers().size();
      } else {
        Utils.userId = 0;
      }
      System.out.println("Object has been deserialized");
      in.close();
      file.close();
      return deliveryService;
    } catch (IOException | ClassNotFoundException ex) {
      System.out.println("IOException is caught");
      UserService userService = new UserServiceImpl();
      userService.register(new User("admin", "admin", Role.ADMIN));
      userService.register(new User("client", "client", Role.CLIENT));
      userService.register(new User("client1", "client1", Role.CLIENT));
      userService.register(new User("client2", "client2", Role.CLIENT));
      userService.register(new User("employee", "employee", Role.EMPLOYEE));
      Utils.menuItemId = 0;
      Utils.orderId = 0;
      Utils.userId = 0;
      deliveryService = new DeliveryService(userService);
      serialize(deliveryService);
      return deliveryService;
    }
  }
}
