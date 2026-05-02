package Models;

    public class User {
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String customerId;
        private final String postalCode;
        private final String password;
        private final String confirmPassword;

        public User(String firstName, String lastName, String email,
                    String customerId, String postalCode,
                    String password, String confirmPassword) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.customerId = customerId;
            this.postalCode = postalCode;
            this.password = password;
            this.confirmPassword = confirmPassword;
        }

        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getEmail() { return email; }
        public String getCustomerId() { return customerId; }
        public String getPostalCode() { return postalCode; }
        public String getPassword() { return password; }
        public String getConfirmPassword() { return confirmPassword; }
    }

