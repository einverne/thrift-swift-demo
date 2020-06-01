package info.einverne.springboot.demo.model;

import info.einverne.springboot.demo.iface.Alarm;
import info.einverne.springboot.demo.iface.Vehicle;

/**
 * @since 2020-06-01
 */
public class Car implements Vehicle, Alarm {

  @Override
  public double getSpeed() {
    return 0;
  }

  @Override
  public void turnOnAlarm() {
    Vehicle.super.turnOnAlarm();
    Alarm.super.turnOnAlarm();
  }

  @Override
  public void turnOffAlarm() {
    Vehicle.super.turnOffAlarm();
    Alarm.super.turnOffAlarm();
  }
}
