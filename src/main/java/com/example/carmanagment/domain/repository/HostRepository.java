package com.example.carmanagment.domain.repository;

import com.example.carmanagment.domain.models.Host;
import com.example.carmanagment.domain.models.HostId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, HostId> {
}
