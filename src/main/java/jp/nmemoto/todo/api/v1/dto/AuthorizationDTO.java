package jp.nmemoto.todo.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationDTO {
    String code;
    String redirectUri;
    String refreshToken;
}
