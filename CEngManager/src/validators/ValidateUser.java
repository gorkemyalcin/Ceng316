package validators;

public class ValidateUser {

    public boolean validate(String username, String password) {
        if (username != null && password != null)
            return true;

        return false;
    }
}
