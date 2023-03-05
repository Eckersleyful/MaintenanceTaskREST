package etteplan.rest.task;

import etteplan.rest.task.models.MaintenanceTask;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MaintenanceTaskService {


    @Autowired
    private MaintenanceTaskRepository repository;


    public List<MaintenanceTask> listAllTasks(){
        return repository.findAll();
    }

    public Optional<MaintenanceTask> get(Integer id){
        return repository.findById(id);
    }

    public MaintenanceTask save(MaintenanceTask task){
        return repository.save(task);
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }

    public List<MaintenanceTask> listBasedOnDeviceName(String deviceName){

        return repository.findByDeviceName(deviceName);

    }
    public List<MaintenanceTask> listBasedOnDeviceType(String deviceType){

        return repository.findByDeviceType(deviceType);

    }
}
