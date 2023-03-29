package com.pfcti.springdata.springjms.dto;

import lombok.Data;

@Data
public class NotificationDto {
    private String phoneNumber;
    private String mailBody;
}
