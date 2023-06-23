package com.example.bookmanagement.user;

public class UserFactory {

    public static User createUser (String name, String email, String phone) {
        return User.builder()
                .name( name )
                .email( email )
                .phone( phone )
                .build();
    }
}
