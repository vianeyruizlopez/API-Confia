package org.example.controller;

import ch.qos.logback.core.Context;

public class TarjetaController {
    public void obtenerTarjeta(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));

    }
}
