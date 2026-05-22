package org.example.healthcare.auth;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.Model.UserApp;
import org.example.healthcare.Repository.UserAppRepository;
import org.example.healthcare.auth.dto.UserLogin;
import org.example.healthcare.auth.dto.UserResponse;
import org.example.healthcare.auth.dto.UserSignUp;
import org.example.healthcare.security.CustomUserDetailsService;
import org.example.healthcare.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAppRepository userAppRepository;
    private final JwtService jwtService;
    private final PasswordEncoder motdePasseEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    public UserResponse authenticat(String nom, String password) {
        UserApp userApp = userAppRepository.findUserAppByNom(nom);
        if (userApp == null || !motdePasseEncoder.matches(password, userApp.getPassword())) {
            throw new RuntimeException("Nom ou mot de passe incorrect");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userApp.getNom());
        return new UserResponse(jwtService.generateToken(userDetails));
    }


    public UserResponse register(UserSignUp userSignUp){
        if(userAppRepository.findUserAppByNom(userSignUp.getNom()) != null){
            throw new RuntimeException("Nom déjà utilisé");
        }
        if (userAppRepository.findUserAppByEmail(userSignUp.getEmail())!=null){
            throw new RuntimeException("Email déjà utilisé");
        }
        UserApp userApp = new UserApp();
        userApp.setNom(userSignUp.getNom());
        userApp.setEmail(userSignUp.getEmail());
        userApp.setPassword(motdePasseEncoder.encode(userSignUp.getPassword()));
        userApp.setRole(userSignUp.getRole());
        userAppRepository.save(userApp);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userApp.getNom());
        String token = jwtService.generateToken(userDetails);
        return new UserResponse(token);
    }

    public UserResponse login(UserLogin request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getNom(), request.getPassword())
        );
        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        return new UserResponse(token);
    }



//    public String authenticateByEmail(String email, String password) {
//        UserApp user = userAppRepository.findUserAppByEmail(email);
//        if (user == null || !motdePasseEncoder.matches(password, user.getPassword())) {
//            throw new RuntimeException("Email ou mot de passe incorrect");
//        }
//        return jwtService.generateToken(user.getNom());
//    }

}
