package org.example.keephealthy02.Api;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Sample 类用于提供访问外部API所需的常量和方法。
 */
public class Sample {
    // 创建一个OkHttpClient实例用于HTTP请求
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    /**
     * 获取访问令牌。
     * 本方法向百度AI平台发送POST请求，以获取访问令牌。
     *
     * @return 访问令牌字符串。
     * @throws IOException 如果请求过程中发生IO异常。
     */
    static String getAccessToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=ZjAl8KQs9l1lubwJG3gjSHtI&client_secret=SHEpV5BNdMRhYjBLrttu6fkoiII7R3yx")
                .method("POST", body)
                .build();

        Response response = HTTP_CLIENT.newCall(request).execute();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response.body().string());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            return jsonObject.getString("access_token");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 主函数：演示如何使用获取到的访问令牌来调用问答服务。
     *
     * @param args 命令行参数（未使用）。
     * @throws IOException 如果请求过程中发生IO异常。
     */
    public static void main(String[] args) throws IOException {
        // 获取访问令牌
        String accessToken = getAccessToken();
        System.out.println(accessToken);
        // 使用获取到的访问令牌调用你的问答服务...

        // 从控制台读取用户输入，实现问答交互
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        System.out.println("欢迎使用文心一言智能问答，请输入你的问题:");

        String question;
        while ((question = reader.readLine()) != null && !question.equalsIgnoreCase("exit")) {
            // 调用问答服务
            String apiUrl = " https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions";

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"question\":\"" + question + "\"}");
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer " + accessToken) // 在请求中添加访问令牌
                    .build();

            Response response = HTTP_CLIENT.newCall(request).execute();
            String responseString = response.body().string();

            // 处理问答服务的响应
            JSONObject jsonResponse = null;
            try {
                jsonResponse = new JSONObject(responseString);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            String answer = jsonResponse.optString("answer", "未找到答案。");

            // 输出答案，并等待用户继续提问
            System.out.println("答案: " + answer);
            System.out.println("请继续提问，或输入 'exit' 退出:");
        }
    }
}