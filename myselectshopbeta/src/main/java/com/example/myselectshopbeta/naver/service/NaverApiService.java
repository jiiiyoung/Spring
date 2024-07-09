package com.example.myselectshopbeta.naver.service;


import com.example.myselectshopbeta.naver.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j // 로깅 처리를 할 때,
@Service
public class NaverApiService {
    public List<ItemDto> searchItems(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "");
        headers.add("X-Naver-Client-Secret", "");;
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?display=15&query=" + query, HttpMethod.GET, requestEntity, String.class);

        HttpStatusCode httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        log.info("NAVER API Status Code :" + status);

        String response = responseEntity.getBody();

        return fromJSONtoItems(response);
    }

    public List<ItemDto> fromJSONtoItems(String response) {
        JSONObject rjson = new JSONObject(response);
        JSONArray items = rjson.getJSONArray("items");
        List<ItemDto> itemDtoList = new ArrayList<>();

        for ( int i = 0; i < items.length(); i++ ) {
            JSONObject itemJson = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);
            }

        return itemDtoList;
    }
}
