package com.crs.heatmap.controller;


import com.crs.heatmap.service.HeatMapService;
import com.crs.heatmap.dto.HeatMapResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/heat-map")
public class HeatMapController {

    @Autowired
    HeatMapService heatMapService;

    @GetMapping("{orderStatus}")
    public List<HeatMapResponseDTO> getHeatMap(@PathVariable("orderStatus") String orderStatus){
        List<HeatMapResponseDTO> heatMapResponseDTOList;
        heatMapResponseDTOList= heatMapService.getHeatMap(orderStatus);
        return  heatMapResponseDTOList;
    }
}
