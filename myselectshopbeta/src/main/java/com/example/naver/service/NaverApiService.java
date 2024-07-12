package com.example.naver.service;


import com.example.naver.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j // 롬복 어노테이션) 로그 선언 자동 생성
@Service
public class NaverApiService {
    public List<ItemDto> searchItems(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "64GPbBhToqmHeeroEJya");
        headers.add("X-Naver-Client-Secret", "5yaoJO3PyI");;
        String body = "";


        // HttpEntity : HTTP요청 및 응답을 표현하는 클래스, 이 클래스는 HTTP요청 및 응답의 본문(body)과 헤더(headers)를 포함
        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);

        // ResponseEntity : HTTP응답을 표현하는데 사용. 응답상태코드, 응답헤더, 응답 본문을 포함.
        // RestTemplate를 사용해 API호출(rest.exchange)
        ResponseEntity<String> responseEntity
                = rest.exchange(
                        "https://openapi.naver.com/v1/search/shop.json?display=15&query=" + query,
                        HttpMethod.GET,
                        requestEntity,
                        String.class
                );

        // 응답 상태코드
        HttpStatusCode httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();

        // 로그 남기기
        log.info("NAVER API Status Code :" + status);

        String response = responseEntity.getBody();

        return fromJSONtoItems(response);
    }


    // 응답을 ItemDto 객체의 리스트로 변환하는 기능
    public List<ItemDto> fromJSONtoItems(String response) {
        // JSONObject : JSON형식의 데이터를 다루기 위한 클래스, response문자열을 JSON객체로 반환
        JSONObject rjson = new JSONObject(response);

        // 응답받아 JSON 객체를 변환 한 것 중 items라는 키를 가진 배열을 추출
        JSONArray items = rjson.getJSONArray("items");

        // ItemDto를 저장할 리스트 선언
        List<ItemDto> itemDtoList = new ArrayList<>();

        // 반복문을 통해 ItemDto타입으로 변경하여 itemDtoList에 저장
        for ( int i = 0; i < items.length(); i++ ) {
            JSONObject itemJson = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);
        }

        return itemDtoList;
    }
}
