package com.example.myselectshopbeta.naver.controller;


import com.example.myselectshopbeta.naver.dto.ItemDto;
import com.example.myselectshopbeta.naver.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Http요청에 대한 응답을 JSON형식으로 반환하는 역할
@RestController
// "/api"로 들어오는 요청은 모두 이 클래스가 처리한다는 의미
@RequestMapping("/api")
@RequiredArgsConstructor // 롬북 어노테이션) final키워드가 붙은 필드들 생성자 자동 생성
public class NaverApiController {
    private final NaverApiService naverApiService;

    // 실제 경로는 "/api/search"가 된다. (@RequestMapping 어노테이션으로 인해)
    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam String query) {
        return naverApiService.searchItems(query);
    }

}
