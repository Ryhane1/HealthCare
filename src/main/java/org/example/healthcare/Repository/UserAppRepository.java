package org.example.healthcare.Repository;

import org.example.healthcare.Model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {


    UserApp findUserAppByEmail(String email);

    UserApp findUserAppByNom(String nom);
}
