package com.sk.community.community;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.community.community.dto.AccessTokenDto;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 测试GithubProvider
     */
    @Test
    void testGithubProvider() {

        String url = "https://github.com/login/oauth/access_token";
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setState("1");
        accessTokenDto.setCode("d93c9d7f2fae87f6a8cd");
        String json = null;
        try {
            json = mapper.writeValueAsString(accessTokenDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }

}
