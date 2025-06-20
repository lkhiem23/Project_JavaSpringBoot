package com.lehoaikhiem.payload.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // Lombok: Tự động tạo constructor với tất cả các field
public class MessageResponse {
    private String message;
}
