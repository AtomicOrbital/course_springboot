// package com.example.course_springboot.configuration;
//
// import com.example.course_springboot.dto.request.IntrospectRequest;
// import com.example.course_springboot.service.AuthenticationService;
// import com.nimbusds.jose.JOSEException;
// import lombok.*;
// import lombok.experimental.FieldDefaults;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
// import org.springframework.security.oauth2.jwt.Jwt;
// import org.springframework.security.oauth2.jwt.JwtDecoder;
// import org.springframework.security.oauth2.jwt.JwtException;
// import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
// import org.springframework.stereotype.Component;
//
// import javax.crypto.spec.SecretKeySpec;
// import java.text.ParseException;
//
// @Component
// public class CustomJwtDecoder implements JwtDecoder {
//    @Value("${jwt.signerKey}")
//    private String signerKey;
//
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    private NimbusJwtDecoder nimbusJwtDecoder = null;
//
//    @Override
//    public Jwt decode(String token) throws JwtException {
////        try {
////            var response = authenticationService.introspect(IntrospectRequest.builder()
////                    .token(token)
////                    .build());
////            if (!response.isValid()) {
////                throw new JwtException("Token invalid");
////            }
////        } catch (JOSEException | ParseException e) {
////            throw new RuntimeException(e);
////        }
//        try {
//            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
//            nimbusJwtDecoder = NimbusJwtDecoder
//                    .withSecretKey(secretKeySpec)
//                    .macAlgorithm(MacAlgorithm.HS512)
//                    .build();
//            return nimbusJwtDecoder.decode(token);
//        }catch (JwtException e){
//            throw new JwtException("Invalid JWT token: " + e.getMessage());
//        }
//
//    }
// }
