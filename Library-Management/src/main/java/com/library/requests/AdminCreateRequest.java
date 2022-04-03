package com.library.requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminCreateRequest {

    private String name;
    private String email;
    private String password;
}
