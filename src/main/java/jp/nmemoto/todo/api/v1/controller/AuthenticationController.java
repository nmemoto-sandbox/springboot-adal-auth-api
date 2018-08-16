package jp.nmemoto.todo.api.v1.controller;

import com.microsoft.aad.adal4j.AuthenticationResult;
import jp.nmemoto.todo.api.v1.dto.AuthenticationConfigDTO;
import jp.nmemoto.todo.api.v1.dto.AuthorizationDTO;
import jp.nmemoto.todo.domain.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    TokenService tokenService;

    @Autowired
    public AuthenticationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/auth_server/config")
    public AuthenticationConfigDTO configurations(){
        return tokenService.getServerDetails();
    }


    @PostMapping("/access_token")
    public AuthenticationResult authorizeToken(@RequestBody @Valid AuthorizationDTO authorizationCode) throws Exception {
        return tokenService.getAccessTokenFromAuthorizationCode(authorizationCode.getCode(), authorizationCode.getRedirectUri());
    }

    @PostMapping("/refresh/access_token")
    public AuthenticationResult refreshToken(@RequestBody @Valid AuthorizationDTO authorizationCode) throws Exception {
        return tokenService.getAccessTokenFromRefreshToken(authorizationCode.getRefreshToken(), authorizationCode.getRedirectUri());
    }

}