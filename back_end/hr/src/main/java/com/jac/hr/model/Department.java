package com.jac.hr.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department {
    private int depId;
    private String depName;
    private String depAddress;
    private String depPhone;
}
