package mk.ukim.finki.wp.jan2022.g2.config;



/**
 * This class is used to configure user login on path '/login' and logout on path '/logout'.
 * The only public page in the application should be '/'.
 * All other pages should be visible only for the authenticated users.
 * Furthermore, in the "list.html" template, the 'Edit', 'Delete', 'Add' buttons should only be
 * visible for a user with role 'ROLE_MANAGER'.
 * <p>
 * For login the employees from the database should be used, where username should be the email.
 */

public class SecurityConfig {

}
