package etteplan.rest.device;


import etteplan.rest.device.models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tinylog.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class DeviceController {

    private static final int CSV_LINE_ELEMENTS = 3;


    @Autowired
    private DeviceService service;

    @GetMapping("/devices")
    public List<Device> getDevices(){
        return service.listAllDevices();
    }


    @PostMapping("/devices/populate")
    public ResponseEntity<String> add(){

        //Only populate the table if it is empty
        if(service.listAllDevices().size() != 0){
            Logger.info("Table not empty, not populating");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Table not empty, not populating");
        }

        URL resource = this.getClass().getResource("/data/devicedata.csv");
        Logger.info("Trying to read device data CSV");

        try {

            InputStream in = getClass().getResourceAsStream("/data/devicedata.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            //Skip the header
            reader.readLine();

            String line;

            while((line = reader.readLine()) != null){

                String[] splitLine = line.split(",");

                if(splitLine.length != CSV_LINE_ELEMENTS){
                    continue;
                }

                String deviceName = splitLine[0], deviceType = splitLine[2];
                Integer deviceYear = Integer.valueOf(splitLine[1]);

                Device device = new Device(deviceName, deviceYear, deviceType);
                service.save(device);
            }

            Logger.info("Data read from file to db succesfully");
            return ResponseEntity.status(HttpStatus.OK).body("Data read from file to db succesfully");

        } catch (IOException e) {
            Logger.error("Reading csv datafile failed: \n" + e.getMessage());
        } catch (FileSystemNotFoundException e){
            Logger.error("CSV file not found: \n", e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Reading and inserting data from file failed");

    }



}
