package com.example.myselectshopbeta.myselectshopbeta.naver.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter // 자동으로 getter 생성
@NoArgsConstructor  // 아무것도 없는 생성자 생성
public class ItemDto {
    private String title;
    private String link;
    private String image;
    private int lowprice;

    // JSON 타입의 데이터를 ItemDto의 형식으로 변경(Stirn, Int타입으로)
    public ItemDto(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.lowprice = itemJson.getInt("lprice");
    }

}
