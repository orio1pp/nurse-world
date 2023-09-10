package nurse.world.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class JwtService {
    public String generateJwt(String role, String username){
        List<String> userRoles = new ArrayList<>(List.of(role.split(",")));
        userRoles.add("user");
        Set<String> roles = new HashSet<>(userRoles);
        return Jwt.issuer("nurse-world-jwt")
                .upn(username)
                .subject("nurse-world-jwt")
                .groups(roles)
                .expiresAt(
                        System.currentTimeMillis() + 3600
                )
                .sign();
    }
}
