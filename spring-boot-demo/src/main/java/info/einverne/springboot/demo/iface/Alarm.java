package info.einverne.springboot.demo.iface;

/**
 * @since 2020-06-01
 */
public interface Alarm {

  default void turnOnAlarm() {
    System.out.println("On");
  }

  default void turnOffAlarm() {
    System.out.println("Off");
  }
}
