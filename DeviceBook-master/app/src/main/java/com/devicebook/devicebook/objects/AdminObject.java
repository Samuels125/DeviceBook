package com.devicebook.devicebook.objects;

/**
 * Created by Sam on 25/04/2018.
 */

public class AdminObject {
    private String userName1;
    private String deviceType1;
    private String deviceModel1;
    private String deviceFault1;

public AdminObject(){

}

    public AdminObject(String userName1, String deviceType1, String deviceModel1, String deviceFault1) {
        this.userName1 = userName1;
        this.deviceType1 = deviceType1;
        this.deviceModel1 = deviceModel1;
        this.deviceFault1 = deviceFault1;
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getDeviceType1() {
        return deviceType1;
    }

    public void setDeviceType1(String deviceType1) {
        this.deviceType1 = deviceType1;
    }

    public String getDeviceModel1() {
        return deviceModel1;
    }

    public void setDeviceModel1(String deviceModel1) {
        this.deviceModel1 = deviceModel1;
    }

    public String getDeviceFault1() {
        return deviceFault1;
    }

    public void setDeviceFault1(String deviceFault1) {
        this.deviceFault1 = deviceFault1;
    }
}

