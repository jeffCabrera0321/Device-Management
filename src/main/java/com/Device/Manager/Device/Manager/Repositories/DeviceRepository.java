package com.Device.Manager.Device.Manager.Repositories;

import com.Device.Manager.Device.Manager.Models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
}
