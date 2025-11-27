package ma.fstt.authservice.security;

import ma.fstt.authservice.model.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.Set;

public class MetamaskUserPrincipal implements Authentication {

    private final UserDto user;
    private final Set<GrantedAuthority> authorities;
    private boolean authenticated = true;

    public MetamaskUserPrincipal(UserDto user) {
        this.user = user;
        this.authorities = user.roles().stream()
                .map(role -> (GrantedAuthority) () -> role)
                .collect(java.util.stream.Collectors.toSet());
    }

    public UserDto getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null; // pas utilisé ici
    }

    @Override
    public Object getDetails() {
        return null; // optionnel
    }

    @Override
    public Object getPrincipal() {
        return this; // ou user si tu préfères
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.wallet(); // utiliser le wallet comme nom
    }
}
