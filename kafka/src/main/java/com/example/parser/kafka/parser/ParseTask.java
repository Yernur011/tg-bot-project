package com.example.parser.kafka.parser;

import com.example.parser.kafka.dto.Product;
import com.example.parser.kafka.dto.Query;
import com.example.parser.kafka.dto.UsersProducts;
import com.example.parser.kafka.dto.UsersQuery;
import com.example.parser.kafka.sevice.UsersProductsService;
import com.example.parser.kafka.sevice.kafka.KafkaSenderExample;
import com.example.parser.kafka.sevice.ProductService;
import com.example.parser.kafka.sevice.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class ParseTask {

    private final ProductService service;
    private final UserQueryService userQueryService;
    private final KafkaSenderExample kafkaSenderExample;
    private final UsersProductsService usersProductsService;


    @Scheduled(fixedDelay = 1000 * 30)
    public void parsProduct() throws IOException {
        log.info("scheduler started");
        List<UsersQuery> list = userQueryService.
                findAllQueries().collectList().block();

        if (!list.isEmpty()) {
            for (UsersQuery el : list) {
                var usersProducts = UsersProducts.builder()
                        .productList(getProductsByChat(el.getQuery(), el.getChatId()))
                        .chatId(el.getChatId())
                        .build();

                kafkaSenderExample.sendMessage("to_telegram", usersProducts);
            }
        }

    }


    public List<Product> getProductsByChat(Query query, Long chatId) throws IOException {
        List<Product> productList = new ArrayList<>();
        String url = "https://www.olx.kz/" + (query).getQueryCategory() + (query).getQueryCity() + (query).getQueryText();
        Document document = Jsoup.connect(url).get();
        Elements products = document.getElementsByClass("css-rc5s2u");
        for (Element product : products) {
            String link = product.attr("href");
            log.info("Link {}", link);

            String title = product.selectFirst("h6.css-16v5mdi").text();
            log.info("Name {}", title);

            String price = product.selectFirst("p.css-10b0gli").text();
            log.info("Цена: {}", price);

            String status = product.selectFirst("span.css-3lkihg").text();

            String address = product.selectFirst("p.css-1a4brun").text();
            log.info("Адрес: {}", address);

            var p = Product.builder()
                    .status(status)
                    .productName(query + " " + title)
                    .productLink("olx.kz" + link)
                    .address(address).build();

            usersProductsService.doesProductExist(p, chatId).subscribe(result -> {
                System.out.println("hello " + result);
                        if (result.equals(false)) {

                            Mono<UsersProducts> byUserId = usersProductsService.findByUserId(chatId);
                            byUserId.subscribe(usersProducts -> System.out.println(usersProducts));
                            byUserId.flatMap(usersProducts -> {
                                usersProducts.getProductList().add(p);
                                System.out.println("usersProducts is updated");
                                return usersProductsService.save(byUserId);
                            });
                        }
                    }
            );
        }
        return usersProductsService.findByUserId(chatId)
                .map(UsersProducts::getProductList)
                .block();
    }

}
