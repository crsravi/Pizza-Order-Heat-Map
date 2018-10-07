package com.crs.heatmap;

import com.crs.heatmap.controller.HeatMapController;
import com.crs.heatmap.dto.HeatMapResponseDTO;
import com.crs.heatmap.service.HeatMapService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = HeatMapController.class, secure = false)
public class HeatMapControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeatMapService heatMapService;

    List<HeatMapResponseDTO> heatMapResponseDTOListMock = new ArrayList<HeatMapResponseDTO>();

    HeatMapResponseDTO heatMapResponseDTO = new HeatMapResponseDTO();




    @Test
    public void getHeatMapTest() throws Exception {

        heatMapResponseDTO.setOrderStatus("Complete");
        heatMapResponseDTO.setLatitutude("35.227085");
        heatMapResponseDTO.setLongitude("-80.843124");
        heatMapResponseDTO.setNumberOfOrders(3L);
        heatMapResponseDTO.setRadius(5L);
        heatMapResponseDTO.setRegionName("Charlotte");

        heatMapResponseDTOListMock.add(heatMapResponseDTO);


        Mockito.when(
                heatMapService.getHeatMap(Mockito.anyString())).thenReturn(heatMapResponseDTOListMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/heat-map/Completed");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"numberOfOrders\":3,\"regionName\":\"Charlotte\",\"latitutude\":\"35.227085\",\"longitude\":\"-80.843124\",\"radius\":5,\"orderStatus\":\"Complete\"}]";


        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

}

