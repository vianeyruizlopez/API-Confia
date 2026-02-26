package org.example;

import io.javalin.Javalin;
import org.example.routers.TarjetaRouter;

public class Main {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> it.anyHost());
            });
        }).start(7000);
        TarjetaRouter router = new TarjetaRouter();
        router.registrarRutas(app);

        System.out.println("Servidor Tarjetas listo en el puerto 7000");

    }
}