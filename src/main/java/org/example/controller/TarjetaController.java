package org.example.controller;

import io.javalin.http.Context;
import org.example.model.Tarjeta;
import org.example.service.TarjetaService;

public class TarjetaController {
    private final TarjetaService service = new TarjetaService();

    public void obtenerTodos(Context ctx) {
        ctx.json(service.obtenerTodoTarjetas());
    }

    public void buscarPorCategoria(Context ctx) {
        String categoria = ctx.queryParam("categoria");
        ctx.json(service.buscarPorCategoria(categoria));
    }

    public void unaTarjeta(Context ctx) {
        String categoria = ctx.queryParam("categoria");
        Tarjeta t = service.unaTarjetaAlAzar(categoria);
        if (t != null) {
            ctx.json(t);
        } else {
            ctx.status(404).result("No se encontraron tarjetas");
        }
    }

    public void agregar(Context ctx) {
        Tarjeta nueva = ctx.bodyAsClass(Tarjeta.class);
        service.agregarTarjeta(nueva);
        ctx.status(201).result("Creada con éxito");
    }

    public void actualizarTarjeta(Context ctx) {
        Tarjeta editada = ctx.bodyAsClass(Tarjeta.class);
        service.actualizarTarjeta(editada);
        ctx.status(200).result("Actualizada");
    }

    public void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        service.eliminarTarjeta(id);
        ctx.status(200).result("Eliminada");
    }
}
