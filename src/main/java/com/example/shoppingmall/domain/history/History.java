package com.example.shoppingmall.domain.history;

import com.example.shoppingmall.domain.user.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity // DB에 테이블 자동 생성
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;


    private String itemName; // 구매한 아이템 명
    private int price; // 구매한 아이템 가격
    private int count; // 구매한 아이템 수량

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 구매 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }

}