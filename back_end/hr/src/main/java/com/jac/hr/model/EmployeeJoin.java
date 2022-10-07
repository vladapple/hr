package com.jac.hr.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeJoin {
    private int id;
    private String firstname;
    private String lastname;
    private String depName;
    private String depPhone;
    private String posName;
    private String senName;
}
