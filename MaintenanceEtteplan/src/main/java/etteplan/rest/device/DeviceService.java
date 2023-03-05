package etteplan.rest.device;

import etteplan.rest.device.models.Device;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional
public class DeviceService {


    @Autowired
    private DeviceRepository repository;

    public Device getById(Integer id){
        return repository.findById(id).get();
    }

    public Device getByName(String name) {

        List<Integer> foundId =  repository.findIdByName(name);

        if(foundId.size() == 0){
            return null;
        }

        return getById(foundId.get(0));
    }

    public void save(Device device){
        repository.save(device);
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }

    public List<Device> listAllDevices(){
        return repository.findAll();
    }




}
