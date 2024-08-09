package com.Device.Manager.Device.Manager.Controllers;

import com.Device.Manager.Device.Manager.Services.ApiService;
import com.Device.Manager.Device.Manager.Services.DeviceService;
import com.Device.Manager.Device.Manager.Models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/DeviceManager")
public class DeviceController {
    private final String notificationHttp = "http://localhost:8082/api/sendEmail";

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ApiService apiService;

   @GetMapping("/home")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Device Controller");
    }

    @GetMapping("/allDevices")
    public List<Device> getAllDevices(){
        return deviceService.getAllDevices();
    }

    @GetMapping("/device/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable UUID id){
        Optional<Device> device = deviceService.getDeviceById(id);
        return device.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addDevice")
    public ResponseEntity<Device> addDevice(@RequestBody Device device){
       StringBuilder body = new StringBuilder();
       try {
           Device newdevice = deviceService.saveDevice(device);
           body.append("{\"Status\": \"Success\",");
           body.append("\"Id\": \"").append(newdevice.getId()).append("\",");
           body.append("\"Name\": \"").append(newdevice.getName()).append("\",");
           body.append("\"Manufacturer\": \"").append(newdevice.getManufacturer()).append("\",");
           body.append("\"Version Number\": \"").append(newdevice.getVersionNumber()).append("\",");
           body.append("\"Description\": \"").append(newdevice.getDescription()).append("\",");
           body.append("\"Location\":\"").append(newdevice.getLocation()).append("\",");
           body.append("\"Email\":\"").append("emailAddress@gmail.com\",");
           body.append("\"Message\":\"").append("Successful Connection\"}");
           System.out.println(body.toString());
           apiService.sendMessage(notificationHttp, body.toString());
           return ResponseEntity.ok(newdevice);
       }
       catch (Exception e){
           body.append("{\"Status\": \"Fail\",");
           body.append("\"Name\": \"").append(device.getName()).append("\",");
           body.append("\"Manufacturer\": \"").append(device.getManufacturer()).append("\",");
           body.append("\"Version Number\": \"").append(device.getVersionNumber()).append("\",");
           body.append("\"Description\": \"").append(device.getDescription()).append("\",");
           body.append("\"Location\":\"").append(device.getLocation()).append("\",");
           body.append("\"Email\":\"").append("emailAddress@gmail.com\",");
           body.append("\"Message\":\"").append("Unsuccessful Connection\"}");
           apiService.sendMessage(notificationHttp, body.toString());
           return ResponseEntity.ok(device);
        }

    }

    @PutMapping("/device/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable UUID id, @RequestBody Device device){
        if(deviceService.getDeviceById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        device.setId(id);
        Device updatedDevice = deviceService.saveDevice(device);
        return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/device/{id}")
    public ResponseEntity<Void> deviceDevice(@PathVariable UUID id){
        if (deviceService.getDeviceById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
