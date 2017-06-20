/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring.echo;

import java.util.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        String[] greetingListReturn = {"ya","oit","whats up"};
        String[] thanksListReturn = {"ya sama-sama","sama-sama bosku"};
        String msg = event.getMessage().getText();
        String msgReturn = "";
        String greetingConstants = "halo|hallo|hello|allo|hai|hy|hi|hey|oi|kerang ajaib|halo kerang ajaib|hy kerang|cuy|bro";
        String thanksConstants = "thanks|ok thanks|thank you|terima kasih|makasih|makasih ya|ok makasih";
        
        Random rand = new Random();
        int random = rand.nextInt(4);

        if (greetingConstants.contains(msg.toLowerCase())) {
            msgReturn = greetingListReturn[rand.nextInt(greetingListReturn.length)];
        }
        else if (thanksConstants.contains(msg.toLowerCase())) {
            msgReturn = thanksListReturn[rand.nextInt(thanksListReturn.length)];
        }
        else if (msg.contains("lakukan") || msg.contains("ngapain")) {
            msgReturn = "tidak ada";
        }
        else if (msg.contains("nanya") || msg.contains("tanyakan")) {
            msgReturn = "iya silakan";
        }
        else if (msg.contains("makan") || msg.contains("minum") || msg.contains("cafe") || msg.contains("resto") || msg.contains("lapar") || msg.contains("laper")) {
            msgReturn = "cari aja di https://www.zomato.com/";
        }
        else if (msg.contains("bohong") || msg.contains("boong")) {
            msgReturn = "tidak";
        }
        else if (msg.contains("bodoh") || msg.contains("stupid") || msg.contains("tolol") || msg.contains("dungu") || msg.contains("bego") || msg.contains("anjing") || msg.contains("fuck") || msg.contains("asshole") || msg.contains("bitch")) {
            msgReturn = "jangan kasar!";
        }
    
        else if (random == 2) {
            msgReturn = "iya";
        }
        else if (random == 1) {
            msgReturn = "tidak";
        }
        else if (random == 0) {
            msgReturn = "tidak tau";
        }
        else {
            msgReturn = "coba tanya yg lain";
        }
        return new TextMessage(msgReturn);

        //return new TextMessage(event.getMessage().getText() + " -dari ea");
        
        
        //return new TextMessage(Integer.toString(random));
        
        
        
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
