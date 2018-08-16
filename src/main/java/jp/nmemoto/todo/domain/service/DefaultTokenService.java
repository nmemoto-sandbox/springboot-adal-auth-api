package jp.nmemoto.todo.domain.service;

import com.microsoft.aad.adal4j.AuthenticationResult;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import jp.nmemoto.todo.api.v1.dto.AuthenticationConfigDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

@Service
public class DefaultTokenService implements TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTokenServices.class);

    private TokenGenerator tokenGenerator;

    @Autowired
    DefaultTokenService(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public AuthenticationResult getAccessTokenFromAuthorizationCode(String authorizationCode, String redirectUri) throws Exception {
        AuthorizationCode request = new AuthorizationCode(authorizationCode);
        try {
            return tokenGenerator.getAccessToken(request, redirectUri);
        } catch (Throwable throwable) {
            return throwException(throwable);
        }
    }

    @Override
    public AuthenticationResult getAccessTokenFromRefreshToken(String refreshToken, String redirectUri) {
        try {
            return tokenGenerator.getAccessTokenFromRefreshToken(refreshToken, redirectUri);
        } catch (Throwable throwable) {
            return throwException(throwable);
        }
    }

    @Override
    public AuthenticationConfigDTO getServerDetails() {
        return tokenGenerator.getServerDetails();
    }

    private AuthenticationResult throwException(Throwable throwable) {
        LOGGER.error(String.format("Failed To retrieve access token using refresh token"), throwable.getMessage());
        throw new TokenGenerationException("Users Access Could not be retrieved from Authentication Server");
    }
}
