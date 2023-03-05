package etteplan.rest.task;

import etteplan.rest.device.DeviceService;
import etteplan.rest.device.models.Device;
import etteplan.rest.task.models.MaintenanceTask;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tinylog.Logger;

import java.util.*;


@RestController
@RequestMapping("/tasks")
public class MaintenanceTaskController {


    @Autowired
    private MaintenanceTaskService maintenanceService;

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/list")
    public List<MaintenanceTask> getTasks(
            @RequestParam (required = false) String name,
            @RequestParam (required = false) String type
    ){


        List<MaintenanceTask> allTasks;

        //If no optional query parameters are give, get all tasks
        if(name != null){
            allTasks = maintenanceService.listBasedOnDeviceName(name);
        }
        else if(type != null){
            allTasks = maintenanceService.listBasedOnDeviceType(type);
        }
        else{
            allTasks = maintenanceService.listAllTasks();
        }

        //Sort based on MaintenanceTask compareTo, primarily based on severity, secondarily the newest tasks first
        Collections.sort(allTasks);

        return allTasks;

    }



    @GetMapping("/{id}")
    public MaintenanceTask getTask(@PathVariable Integer id){

        Optional<MaintenanceTask> foundTask = maintenanceService.get(id);

        if(!foundTask.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Task with id %d not found", id));
        }

        return foundTask.get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyTask(@RequestBody @Valid MaintenanceTask task, @PathVariable Integer id){

        Optional<MaintenanceTask> foundTask = maintenanceService.get(id);

        if(!foundTask.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Task to be modified with id %d not found", id));
        }

        //If the task was found and exception wasn't thrown, get the task object from the Optional return value
        MaintenanceTask confirmedTask = foundTask.get();

        //Try to fetch the new device based on the new device name, null checked
        Device newDevice = deviceService.getByName(task.getDeviceName());

        if(newDevice == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The given device with name %s not found", task.getDeviceName()));
        }





        //Set all attributes for the found task and save it into the DB
        confirmedTask.setDevice(newDevice);

        confirmedTask.setIsTaskOpen(task.isTaskOpen());

        confirmedTask.setTaskDescription(task.getTaskDescription());

        confirmedTask.setDeviceId(task.getDeviceName());

        confirmedTask.setTaskSeverity(task.getTaskSeverity());

        confirmedTask.setTaskRegistrationTime(confirmedTask.getTaskRegistrationTime());

        Logger.info("Found task with given id, updating an entry with given data");
        return new ResponseEntity<>(maintenanceService.save(confirmedTask), HttpStatus.CREATED);


    }

    @PostMapping("")
    public ResponseEntity<MaintenanceTask> saveTask(@RequestBody @Valid MaintenanceTask task){

        String deviceName = task.getDeviceName();


        //Instantiate a new Device object based on the given device name in the post request body
        Device foundDevice = deviceService.getByName(deviceName);

        //If no matching device name is found in the DB, a new task will not be created
        if(foundDevice == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The given device with name %s not found", task.getDeviceName()));
        }

        task.setDevice(foundDevice);

        Logger.info("Adding new maintenance task");
        return new ResponseEntity<>(maintenanceService.save(task), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id){

        if(!maintenanceService.get(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Task to be deleted with id %d not found", id));
        }

        Logger.info("Deleting found maintenance task");
        maintenanceService.delete(id);
        return new ResponseEntity<>(String.format("Task deleted with id  %d", id), HttpStatus.OK);


    }


}
