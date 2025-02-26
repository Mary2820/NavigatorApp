package com.solvd.navigationapp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.navigationapp.models.User;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            User user = new User(1L, 1L, "Jan", "Mazowiecki", "jan.maz@example.com", "password123");

            JAXBContext context = JAXBContext.newInstance(User.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(user, new File("user_output.xml"));
            System.out.println("XML saved to user_output.xml");

            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File("user_output.json"), user);
            System.out.println("JSON saved to user_output.json");

            ObjectMapper objectMapper = new ObjectMapper();

            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);

            System.out.println(jsonString);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
