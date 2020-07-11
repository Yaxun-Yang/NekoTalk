package org.csu.nekotalk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerUsers {
    private String momentId;
    private String phoneNumber;
}
