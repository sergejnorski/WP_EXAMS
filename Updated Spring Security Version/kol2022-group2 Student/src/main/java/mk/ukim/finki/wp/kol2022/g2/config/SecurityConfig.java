package mk.ukim.finki.wp.kol2022.g2.config;

import org.springframework.context.annotation.Configuration;
/**
 * This class is used to configure user login on path '/login' and logout on path '/logout'.
 * The only public page in the application should be '/'.
 * All other pages should be visible only for a user with role 'ROLE_ADMIN'.
 * Furthermore, in the "list.html" template, the 'Edit', 'Delete', 'Add' buttons should only be
 * visible for a user with role 'ROLE_ADMIN'.
 * <p>
 * For login the students from the database should be used, where username should be the email.
 */
@Configuration
public class SecurityConfig {


}
