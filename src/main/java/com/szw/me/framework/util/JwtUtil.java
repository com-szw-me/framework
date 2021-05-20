package com.szw.me.framework.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.szw.me.framework.vo.ResultVO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    // 过期时间为1小时
    private static final long EXPIRE_TIME = 1*60*60*1000;
    // token私钥
    private static final String TOKEN_SECRET = "szw";

    /**
     * 生成签名，1小时后过期
     * @param code
     * @param nick
     * @return String
     */
    public static String sign(String code, String nick) {
        // 过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("type", "JWT");
        header.put("algo", "HS256");
        // 附带用户信息生成签名
        return JWT.create().withHeader(header)
                .withClaim("code", code)
                .withClaim("nick", nick)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * token校验
     * @param token
     * @return ResultVO<Map<String, Object>>
     */
    public static ResultVO<Map<String, Object>> verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("code", claims.get("code").asString());
            resultMap.put("nick", claims.get("nick").asString());
            return ResultVOUtil.success(resultMap);
        } catch (JWTVerificationException e) {
            return ResultVOUtil.error("token失效");
        } catch (Exception e) {
            return ResultVOUtil.error("请先登录");
        }
    }
}
