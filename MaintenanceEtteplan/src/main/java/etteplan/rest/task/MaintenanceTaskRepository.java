package etteplan.rest.task;

import etteplan.rest.task.models.MaintenanceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Integer> {


    @Query(nativeQuery = true, value = "SELECT * FROM maintenance_task m INNER JOIN devices d ON m.device_id = d.id WHERE d.name = :name")
    List<MaintenanceTask> findByDeviceName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM maintenance_task m INNER JOIN devices d ON m.device_id = d.id WHERE d.device_type = :type")
    List<MaintenanceTask> findByDeviceType(String type);
}
