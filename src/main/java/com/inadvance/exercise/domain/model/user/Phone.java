package com.inadvance.exercise.domain.model.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    private String number;
    private String cityCode;
    private String countryCode;
}
