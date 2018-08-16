package jp.nmemoto.todo.domain.service;

import com.microsoft.aad.adal4j.AuthenticationResult;
import jp.nmemoto.todo.api.v1.dto.AuthenticationConfigDTO;

public interface TokenService {
    AuthenticationResult getAccessTokenFromAuthorizationCode(String authorizationCode, String redirectUri) throws Exception;

    AuthenticationResult getAccessTokenFromRefreshToken(String refreshToken, String redirectUri);

    AuthenticationConfigDTO getServerDetails();

}
