package com.example.recycleheroes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserService {


    public static UserModel parseJsonResponse(String jsonResponse) {
        Gson gson = new Gson();
        Type type = new TypeToken<UserModel>() {}.getType();
        UserModel response = gson.fromJson(jsonResponse, type);

        return response;


    }
}

