package com.auth.service;

import com.auth.entity.Users;
import com.auth.model.CustomOAuthUser;
import com.auth.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {


    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        String registrationId = request.getClientRegistration().getRegistrationId(); // google or facebook
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String id = oAuth2User.getAttribute("id");

        Users users = userRepository.findByEmail(email).orElseGet(Users::new);

        users.setEmail(email);
        users.setName(name);

        if ("google".equalsIgnoreCase(registrationId)) {
            users.setGoogleId(id);
        } else if ("facebook".equalsIgnoreCase(registrationId)) {
            users.setFacebookId(id);
        }

        userRepository.save(users);

        return new CustomOAuthUser(users, oAuth2User.getAttributes());
    }
}
