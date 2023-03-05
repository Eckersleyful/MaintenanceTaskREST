package etteplan.rest.device.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import etteplan.rest.task.models.MaintenanceTask;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;


@Entity
@Table(name = "devices")
public class Device {

    @NotNull(message = "Device name can not be null")
    private String name;

    @NotNull(message = "Device year can not be null")
    private int year;

    @NotNull(message = "Device type can not be null")
    private String deviceType;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private MaintenanceTask task;



    public Device(String name, int year, String deviceType) {
        this.name = name;
        this.year = year;
        this.deviceType = deviceType;

    }
    public Device(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getDeviceId() {
        return id;
    }

    public void setDeviceId(Integer id) {
        this.id = id;
    }
}
