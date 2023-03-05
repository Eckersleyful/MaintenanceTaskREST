package etteplan.rest.task.models;




import com.fasterxml.jackson.annotation.JsonProperty;
import etteplan.rest.device.models.Device;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;


@Entity
@Table(name = "maintenance_task")

public class MaintenanceTask implements Comparable<MaintenanceTask> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private Integer id;


    @NotBlank(message = "Required target device ID for the task")
    @Column(name = "device_name")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String deviceName;

    @OneToOne
    private Device device;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "task_date")
    private Date taskRegistrationTime;



    @Column(name = "description")
    @NotBlank(message = "Task description cant be blank or null")
    private String taskDescription;

    @Column(name = "is_task_open")
    @NotNull(message = "Required boolean value is the ticket closed or not")
    private boolean isTaskOpen;

    @Min(value = 0, message = "Required a value for severity between 0-2, 0: Unimportant, 1: Important 2: Critical")
    @Max(value = 2, message = "Required a value for severity between 0-2, 0: Unimportant, 1: Important 2: Critical")
    @NotNull(message = "Required a value for severity between 0-2, 0: Unimportant, 1: Important 2: Critical")
    @Column(name = "task_severity")
    private Integer taskSeverity;


    public MaintenanceTask(){
    }


    public void setTaskRegistrationTime(Date taskRegistrationTime) {
        this.taskRegistrationTime = taskRegistrationTime;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setIsTaskOpen(boolean taskOpen) {
        this.isTaskOpen = taskOpen;
    }

    public void setDeviceId(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setTaskSeverity(Integer taskSeverity) {
        this.taskSeverity = taskSeverity;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Integer getId() {
        return id;
    }

    public String getDeviceName() {
        return this.deviceName;
    }


    public boolean isTaskOpen() {
        return isTaskOpen;
    }

    public Date getTaskRegistrationTime() {
        return taskRegistrationTime;
    }


    public String getTaskDescription() {
        return taskDescription;
    }


    public Integer getTaskSeverity() {
        return taskSeverity;
    }

    public Device getDevice() {
        return device;
    }

    @Transient
    @Override
    public int compareTo(MaintenanceTask otherTask) {

        int severityComparison = otherTask.getTaskSeverity().compareTo(this.getTaskSeverity());

        if(severityComparison == 0){
            return otherTask.taskRegistrationTime.compareTo(this.taskRegistrationTime);
        }

        return severityComparison;
    }

}
