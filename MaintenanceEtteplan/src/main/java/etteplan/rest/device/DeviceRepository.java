package etteplan.rest.device;

import etteplan.rest.device.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {


    @Query("SELECT d.id from Device d WHERE d.name = :name")
    List<Integer> findIdByName(@Param("name") String name);


}
