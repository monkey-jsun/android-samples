package net.junsun.l22_ble_scanning;

/**
 * Created by jsun on 5/28/2016.
 */
public class MyBleDevice {
    public String name;
    public String address;
    public int rssi;

    public MyBleDevice(String name, String address, int rssi) {
        this.name = name;
        this.address = address;
        this.rssi = rssi;
    }

    @Override
    public boolean equals(Object o) {
        return address.equals(((MyBleDevice)o).address);
    }
}
