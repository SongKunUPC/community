package com.sk.community.community.controller;

import com.sk.community.community.dto.AccessTokenDto;
import com.sk.community.community.dto.GithubUser;
import com.sk.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by SongKun on 2020/1/19 12:39 下午
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8080/community/callback");
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id("aefd0fcd402f1abd9ba6");
        accessTokenDto.setClient_secret("b4ac102ec43983313acf7356bc9b29afcda80360");
        String accessToken = githubProvider.getAccessToken(new AccessTokenDto());
        GithubUser user = githubProvider.getUser(accessToken);
        return "redirect:index";
    }
}
