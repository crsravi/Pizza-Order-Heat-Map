package com.crs.heatmap.repo;

import com.crs.heatmap.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
