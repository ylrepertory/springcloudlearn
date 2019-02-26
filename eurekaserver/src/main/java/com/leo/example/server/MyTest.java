package com.leo.example.server;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTest extends Thread
{




    public static void main(String[] args)
    {

        ExecutorService executorService= Executors.newFixedThreadPool(100);
        for (int i=0;i<1;i++){
            executorService.submit(new MyThread(i));
      }
      new String().concat()
//       System.out.println(  (new BigDecimal("18.9").doubleValue()*100));
    }


    private static int Right(int [][] array,int i,int j){
        return array[i][j];
    }
    private static int Down(int [][] array,int i,int j){
        return array[j][i];
    }
    private static int Left(int [][] array,int i,int j){
        return array[array.length-1-i][array[i].length-1-j];
    }
    private static int Up(int [][] array,int i,int j){
          return array[array[i].length-1-j][i];
    }

}

class MyThread extends Thread {
    private static int userId;
    public static final String LOGIN_KEY               = "showjoy0515";
    public MyThread(int userId)
    {
        this.userId = userId;
    }
    public void run()
    {
        for (int i=0;i<1000;i++){
            visit();
        }


    }
    private void visit(){
        String userEd="";
        try {
            userEd =encrypt(String.valueOf(userId), LOGIN_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 登陆 Url
        String loginUrl = "https://bbs.battcn.com/resources/1";
        // 需登陆后访问的 Url
        String dataUrl = "https://shopappserver.showjoy.net/app/getWeexConfig?appType=1";
        HttpClient httpClient = new HttpClient();

        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        PostMethod postMethod = new PostMethod(loginUrl);
        GetMethod getMethod2=new GetMethod(loginUrl);
        // 设置登陆时要求的信息，用户名和密码
        NameValuePair[] data = { new NameValuePair("userId", userId+"") };
        postMethod.setRequestBody(data);
        try {
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            int statusCode=httpClient.executeMethod(getMethod2);

//            // 获得登陆后的 Cookie
//            Cookie[] cookies = httpClient.getState().getCookies();
//            StringBuffer tmpcookies = new StringBuffer();
//            for (Cookie c : cookies) {
//                tmpcookies.append(c.toString() + ";");
//                System.out.println("cookies = "+c.toString());
//            }
//            if(statusCode==200){//重定向到新的URL
//                System.out.println("模拟登录成功");
//                // 进行登陆后的操作
//                GetMethod getMethod = new GetMethod(dataUrl);
//                // 每次访问需授权的网址时需带上前面的 cookie 作为通行证
//                getMethod.setRequestHeader("cookie", tmpcookies.toString());
//                // 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
//                // 例如，referer 从哪里来的，UA 像搜索引擎都会表名自己是谁，无良搜索引擎除外
//                postMethod.setRequestHeader("Referer", "http://passport.mop.com/");
//                postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
//                httpClient.executeMethod(getMethod);
//                // 打印出返回数据，检验一下是否成功
//                String text = getMethod.getResponseBodyAsString();
//                System.out.println(text);
//            }
//            else {
//                System.out.println("登录失败");
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        new Base64();
        String strs = Base64.encodeBase64URLSafeString(bt);
        return strs;
    }

    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(key);
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, securekey, sr);
        return cipher.doFinal(data);
    }
}