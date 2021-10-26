package com.itis.stalkershop.services.implementations;

public class SignUpServiceImpl implements SignUpService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    public SignUpServiceImpl(
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder,
            Validator validator
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public void signUp(UserForm form) {
        // passwordEncoder.matches("123123", "HASH");
        Optional<ErrorEntity> optionalError = validator.validateRegistration(form);
        if(optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }
        User user = new User(
                form.getFirstName(),
                form.getLastName(),
                form.getAge(),
                passwordEncoder.encode(form.getPassword()),
                form.getEmail()
        );

        usersRepository.save(user);
    }
}
