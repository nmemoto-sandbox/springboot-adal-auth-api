package jp.nmemoto.todo.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationConfigDTO {
    private String clientId;
    private String tenantId;
    private String authority;
}
