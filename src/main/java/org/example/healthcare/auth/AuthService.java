package org.example.healthcare.auth;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.Model.UserApp;
import org.example.healthcare.Repository.UserAppRepository;
import org.example.healthcare.auth.dto.UserLogin;
import org.example.healthcare.auth.dto.UserResponse;
import org.example.healthcare.auth.dto.UserSignUp;
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

    public UserResponse authenticat(String nom, String password) {
        UserApp userApp = userAppRepository.findUserAppByNom(nom);
        if (userApp == null || !motdePasseEncoder.matches(password, userApp.getPassword())) {
            throw new RuntimeException("Nom ou mot de passe incorrect");
        }
        return new UserResponse(jwtService.generateToken(userApp.getNom())) ;
    }


//
//    public String authenticateByEmail(String email, String password) {
//        UserApp user = userAppRepository.findUserAppByEmail(email);
//        if (user == null || !motdePasseEncoder.matches(password, user.getPassword())) {
//            throw new RuntimeException("Email ou mot de passe incorrect");
//        }
//        return jwtService.generateToken(user.getNom());
//    }



//    public UserResponse registeer(UserSignUp userSignUp) {
//        if (userRepository.findUserByNom(userSignUp.getNom()) != null) {
//            throw new RuntimeException("Nom déjà utilisé");
//        }
//        if (userRepository.findUserByEmail(userSignUp.getEmail()) != null) {
//            throw new RuntimeException("Email déjà utilisé");
//        }
//        var user = new UserApp();
//        user.setNom(userSignUp.getNom());
//        user.setEmail(userSignUp.getEmail());
//        user.setPassword(motdePasseEncoder.encode(userSignUp.getPassword()));
//        userAppRepository.save(user);
//        String token = jwtService.generateToken(user.getNom());
//        return new UserResponse(token);
//    }

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
        userAppRepository.save(userApp);
        String token = jwtService.generateToken(userApp.getNom());
        return new UserResponse(token);

    }


    public UserResponse login(UserLogin request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getNom(), request.getPassword())
        );
        String token = jwtService.generateToken(request.getNom());
        return new UserResponse(token);
    }






    public UserResponse loginx(UserLogin request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getNom(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(
                userDetails.getUsername()
        );

        return new UserResponse(token);
    }


//    public UserResponse login(UserLogin userLogin) {
//        return authenticat(userLogin.getNom(), userLogin.getPassword());
//    }



}
