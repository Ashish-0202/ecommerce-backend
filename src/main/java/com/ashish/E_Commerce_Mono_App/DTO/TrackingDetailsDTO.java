package com.ashish.E_Commerce_Mono_App.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackingDetailsDTO {

    private LocalDateTime last_update;
    private String status;
    private String description;
}
