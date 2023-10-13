package com.nhp.itsocialserver.dtos.responses.stat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserByMonth {
    private String month;
    private Long countUser;
}
