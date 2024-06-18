package com.example.demo.service;

import com.example.demo.model.DataBase.DankookMenu;
import com.example.demo.DTO.ToClient.DankookMenuResponse;
import com.example.demo.repository.DankookMenuRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DankookMenuService {

    @Autowired
    private DankookMenuRepository dankookMenuRepository;

    public List<String> fetchMenu(LocalDate date, String restaurant) throws Exception {
        String restaurantCode = "";
        if (restaurant.equals("혜당관")) {
            restaurantCode = "556";
        } else if (restaurant.equals("교직원")) {
            restaurantCode = "555";
        }

        List<String> allMenuItems = new ArrayList<>();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM월dd일");

        for (int i = 0; i <= 4; i++) {
            LocalDate currentDate = date.plusDays(i);
            String formattedDate = currentDate.format(outputFormatter);

            // 중복 데이터 확인
            if (dankookMenuRepository.existsByDateAndRestaurant(formattedDate, restaurant)) {
                System.out.println("Data for " + formattedDate + " and " + restaurant + " already exists. Skipping...");
                continue;
            }

            String url = "https://www.dankook.ac.kr/web/kor/-" + restaurantCode;

            // URL 출력 확인
            System.out.println("Request URL: " + url);

            Document doc = Jsoup.connect(url).get();
            Elements rows = doc.select("table[summary='요일, 식단메뉴'] tbody tr");

            boolean dateFound = false;
            StringBuilder breakfast = new StringBuilder("[조식] : ");
            StringBuilder lunch = new StringBuilder("[중식] : ");
            StringBuilder dinner = new StringBuilder("[석식] : ");

            for (Element row : rows) {
                String rowDate = row.select("span.name_date").text();

                if (rowDate.equals(formattedDate)) {
                    dateFound = true;

                    Elements tds = row.select("td");
                    for (Element td : tds) {
                        String[] parts = td.html().split("<br>");
                        String lastMealType = null;

                        for (String part : parts) {
                            String cleanedPart = Jsoup.parse(part).text().trim();

                            if (cleanedPart.startsWith("조식") || cleanedPart.startsWith("중식") || cleanedPart.startsWith("석식")) {
                                lastMealType = cleanedPart;
                            } else if (!cleanedPart.isEmpty() && lastMealType != null) {
                                if (cleanedPart.startsWith("기타")) {
                                    if (lastMealType.contains("석식")) {
                                        break;
                                    }
                                } else {
                                    if (lastMealType.contains("조식")) {
                                        breakfast.append(cleanedPart).append(", ");
                                    } else if (lastMealType.contains("중식")) {
                                        lunch.append(cleanedPart).append(", ");
                                    } else if (lastMealType.contains("석식")) {
                                        dinner.append(cleanedPart).append(", ");
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
            }

            if (!dateFound) {
                throw new IllegalArgumentException("No menu data available for the specified date: " + formattedDate);
            }

            // Trim trailing commas and spaces
            if (breakfast.length() > 5) {
                breakfast.setLength(breakfast.length() - 2);
            }
            if (lunch.length() > 5) {
                lunch.setLength(lunch.length() - 2);
            }
            if (dinner.length() > 5) {
                dinner.setLength(dinner.length() - 2);
            }

            // Create DankookMenu entity and save to repository
            DankookMenu dankookMenu = new DankookMenu(formattedDate, restaurant, breakfast.toString(), lunch.toString(), dinner.toString());
            dankookMenuRepository.save(dankookMenu);

            // 최종 리스트 출력
            System.out.println("Final Menu Items for " + formattedDate + ":");
            System.out.println(breakfast);
            System.out.println(lunch);
            System.out.println(dinner);

            // Add to allMenuItems list
            allMenuItems.add(breakfast.toString());
            allMenuItems.add(lunch.toString());
            allMenuItems.add(dinner.toString());
        }

        return allMenuItems;
    }


    public DankookMenuResponse dankookMenuCheck(String date, String restaurant) {
        DankookMenu menu = dankookMenuRepository.findByDateAndRestaurant(date, restaurant);

        if (menu == null) {
            return null; // or throw an exception, depending on your use case
        }

        DankookMenuResponse response = new DankookMenuResponse();
        response.setDate(menu.getDate());
        response.setRestaurantLocation(menu.getRestaurant());
        response.setBreakfast(menu.getBreakfast());
        response.setLunch(menu.getLunch());
        response.setDinner(menu.getDinner());

        return response;
    }

}
