package info.einverne.springboot.demo.iface;

/**
 * @since 2020-06-01
 */
public interface Vehicle {

  double getSpeed();

  default void turnOnAlarm() {
    System.out.println("Vehicle turn alarm on");
  }

  default void turnOffAlarm() {
    System.out.println("Vechicle turn alarm off");
  }
}
