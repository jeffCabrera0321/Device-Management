package com.Device.Manager.Device.Manager.Services;



import com.Device.Manager.Device.Manager.Models.Device;
import com.Device.Manager.Device.Manager.Repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAllDevices(){
        return deviceRepository.findAll();
    }

    public Optional<Device> getDeviceById(UUID id){
        return deviceRepository.findById(id);
    }
    public Device saveDevice(Device device){
        return deviceRepository.save(device);
    }
    public void deleteDevice(UUID id){
        deviceRepository.deleteById(id);
    }
}
