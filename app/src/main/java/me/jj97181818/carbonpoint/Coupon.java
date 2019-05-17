package me.jj97181818.carbonpoint;

import java.util.Random;

public class Coupon {
    public String name, description, date;
    public int point, image;

    public Coupon(String name, String description, String date, int image, int point) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.image = image;
        this.point = point;
    }

    public String code() {
        Random random = new Random();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int number = random.nextInt() * 36;

            if (number > 9) {
                result.append(String.valueOf(number));
            } else {
                result.append((char)(number + 65));
            }
        }

        return result.toString();
    }
}
