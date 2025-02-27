package com.solvd.navigationapp.utils.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import com.solvd.navigationapp.enums.UserType;

public class UserTypeAdapter extends XmlAdapter<String, UserType> {
    
    @Override
    public UserType unmarshal(String value) {
        if (value == null) return null;
        return UserType.valueOf(value);
    }

    @Override
    public String marshal(UserType value) {
        if (value == null) return null;
        return value.name();
    }
} 