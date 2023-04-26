package com.example.myapp7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Repository {
    private static List<Item> fumos;
    private static Map<String, User> users;
    public static void loadFumos(){
        fumos = new ArrayList<>();
        fumos.add(new Item("Reimu", "Shrine maiden", R.drawable.baseline_broken_image_24));
        fumos.add(new Item("Aya", "Bunbunmaru journalist", R.drawable.baseline_broken_image_24));
        fumos.add(new Item("Marisa", "Witch", R.drawable.baseline_broken_image_24));
        fumos.add(new Item("Cirno", "Ice fairy", R.drawable.baseline_broken_image_24));
        fumos.add(new Item("Rumia", "Yokai of darkness", R.drawable.baseline_broken_image_24));
        fumos.add(new Item("Remilia", "Vampire", R.drawable.baseline_broken_image_24));
        fumos.add(new Item("Yuyuko", "Stinky ghost", R.drawable.baseline_broken_image_24));
    }
    public static List<Item> getFumosByLogin(String login){
        if (login.equals("dyaika")){
            return getFumos().subList(0, 5);
        }
        return new ArrayList<Item>();
    }
    public static void loadUsers(){
        users = new HashMap<>();
        users.put("dyaika", new User(
                "8m68aNkg1C0Ac+Vu6hPznp9T6KwohPLtME12aa2n/Cw=\n",
                "yopg3SNiCNdHKw0yOCbCxRGm+4TpUJ+4GDOAYet0tl0=\n",
                "dyaika",
                "jNd4VhZQ1s20oY3YZBjsTg==\n"));
    }
    public static List<Item> getFumos(){
        if (fumos == null){
            loadFumos();
        }
        return fumos;
    }
    public static User getEncryptedUserByLogin(String login){
        if (users == null){
            loadUsers();
        }
        return users.get(login);
    }
}
