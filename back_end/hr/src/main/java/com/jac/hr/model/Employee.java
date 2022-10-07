package com.jac.hr.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private int id;
    private String firstname;
    private String lastname;
    private String startDate;
    private String endDate;
    private String address;
    private String phone;
    private int departmentId;
    private int positionId;
    private int seniorityId;
}
