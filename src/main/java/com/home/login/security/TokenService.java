package com.home.login.security;

import com.home.login.entities.User;
import com.home.login.exception.PublicPrivateKeyErrorException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${security.token.secret}")
    private String secret;

    @Value("${security.key.public}")
    private String publicKeyPath;

    @Value("${security.key.private}")
    private String privateKeyPath;

    public String createToken(UsernamePasswordAuthenticationToken authenticationToken) {
        try {
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject("user")
                    .issuer("login")
                    .expirationTime(new Date(System.currentTimeMillis() + 3600000))
                    .claim("authenticationToken", authenticationToken)
                    .jwtID(UUID.randomUUID().toString())
                    .build();
            JWEHeader header = new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM)
                    .contentType("JWT")
                    .build();
            EncryptedJWT encryptedJWT = new EncryptedJWT(header, claimsSet);
            JWEEncrypter encrypter = new RSAEncrypter(readRSAPublicKeyFromPEMFile());
            encryptedJWT.encrypt(encrypter);
            return encryptedJWT.serialize();
        }catch (Exception e){
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }

    public String getSubject(String tokenJWE){
        try {
            RSAPrivateKey privateKey = readRSAPrivateKeyFromPEMFile();
            JWEObject jweObject = JWEObject.parse(tokenJWE);
            JWEDecrypter decrypter = new RSADecrypter(privateKey);
            jweObject.decrypt(decrypter);
            return jweObject.getPayload().toJSONObject().get("authenticationToken").toString();
        }catch (Exception e){
            throw new RuntimeException("Token JWT expirado ou invalido");
        }
    }

    private RSAPublicKey readRSAPublicKeyFromPEMFile(){
        try {
            String pemContent = new String(Files.readAllBytes(Paths.get(publicKeyPath)));
            pemContent = pemContent.replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
            X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(pemContent));
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) kf.generatePublic(spec);
        }catch (Exception e){
            throw new PublicPrivateKeyErrorException();
        }
    }

    private RSAPrivateKey readRSAPrivateKeyFromPEMFile() throws Exception {
        try{
            PEMParser pemParser = new PEMParser(new FileReader(privateKeyPath));
            PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
            KeyPair keyPair = new JcaPEMKeyConverter().getKeyPair(pemKeyPair);
            return (RSAPrivateKey) keyPair.getPrivate();
        } catch (Exception e){
            throw new PublicPrivateKeyErrorException();
        }
    }
}
