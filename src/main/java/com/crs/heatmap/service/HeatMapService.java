package com.crs.heatmap.service;

import com.crs.heatmap.repo.OrdersRepository;
import com.crs.heatmap.repo.RegionRepository;
import com.crs.heatmap.dto.HeatMapResponseDTO;
import com.crs.heatmap.entity.Orders;
import com.crs.heatmap.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HeatMapService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    RegionRepository regionRepository;

    private final Long MAX_RADIUS= 5L;

    public List <HeatMapResponseDTO> getHeatMap(String orderStatus){
        List<Orders> ordersAll = ordersRepository.findAll();

        List<Orders> inProgressOrders = getOrdersStatus(ordersAll,orderStatus);


        Map<Long, Long> groupCount = groupByRegion(inProgressOrders);

        List<HeatMapResponseDTO> heatMapList = new ArrayList<HeatMapResponseDTO>();
        groupCount.forEach((region,cnt)->{
            HeatMapResponseDTO heatMapEntry =new HeatMapResponseDTO();
            heatMapEntry.setNumberOfOrders(cnt);
            Region reg = regionRepository.getOne(region);
            heatMapEntry.setRegionName(reg.getName());
            heatMapEntry.setLatitutude(reg.getLatitude());
            heatMapEntry.setLongitude(reg.getLongitude());
            heatMapEntry.setOrderStatus(orderStatus);
            heatMapList.add(heatMapEntry);
        });
        return updateRadii(heatMapList);
    }

    private Map<Long, Long> groupByRegion(List<Orders>  ordersList){

        return ordersList.stream().collect(
                Collectors.groupingBy(Orders::getRegion, Collectors.counting()));
    }

    private List<Orders> getOrdersStatus(List<Orders> allOrders,String orderStatus){
        return allOrders.stream().filter(ord-> orderStatus.equals(ord.getStatus())).collect(Collectors.toList());
    }

    private List<HeatMapResponseDTO> updateRadii(List<HeatMapResponseDTO> heatMapList){
        Comparator<HeatMapResponseDTO> comparator = Comparator.comparing( HeatMapResponseDTO::getNumberOfOrders );
        Long maxOrders = heatMapList.stream().max(comparator).get().getNumberOfOrders();
        heatMapList.forEach(heatMap->{
            heatMap.setRadius((heatMap.getNumberOfOrders()*MAX_RADIUS)/maxOrders);
        });

        return heatMapList;
    }
}
