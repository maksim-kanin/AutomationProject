package com.tricentis.demowebshop.utils.credentials.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    private String role;
    private List<User> users;
}
