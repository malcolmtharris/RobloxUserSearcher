package org.example;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class RobloxLookup {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter user ID: ");

        String userId = input.nextLine();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://users.roblox.com/v1/users/" + userId))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        RobloxUser user = gson.fromJson(response.body(), RobloxUser.class);

        System.out.println("Username: " + user.name);
        System.out.println("Display name: " + user.displayName);
        System.out.println("Created: " + user.created);
        System.out.println("Verified: " + user.hasVerifiedBadge);
        System.out.println("Banned: " + user.isBanned);
    }
}