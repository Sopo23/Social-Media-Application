package com.example.twitter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientAuxDTO {
    private UserDto user;
    private String bio;
    private Integer followers;
    private Integer accountsFollowed;
}
