package com.sk.community.community.provider;

//import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.community.community.dto.AccessTokenDto;
import com.sk.community.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by SongKun on 2020/1/19 12:51 下午
 */
@Component
// Component（组件）将类初始化到spring的上下文
public class GithubProvider {

    // 使用jackson
    private ObjectMapper mapper = new ObjectMapper();
    /**
     * 获取accessToken方法
     * @param accessTokenDto Dto实体类
     * @return
     */
    public String getAccessToken(AccessTokenDto accessTokenDto) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        // String json = JSON.toJSONString(accessTokenDto);
        String json = null;
        try {
            json = mapper.writeValueAsString(accessTokenDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        // 代码放置try括号中，结束后释放
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] split = string.split("&");
            String[] split1 = split[0].split("=");
            String token = split1[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            // 将String类型封装为githubUser类型
            // GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            GithubUser githubUser = mapper.readValue(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
