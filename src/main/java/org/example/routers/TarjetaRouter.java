package org.example.routers;

import io.javalin.Javalin;
import org.example.controller.TarjetaController;

public class TarjetaRouter {
private final TarjetaController tarjetaController = new TarjetaController();
    public void registrarRutas(Javalin app) {
        app.get("/tarjetas/todos", tarjetaController::obtenerTodos);
        app.get("/tarjetas/azar", tarjetaController::unaTarjeta);
        app.get("/tarjetas/buscar", tarjetaController::buscarPorCategoria);
        app.post("/tarjetas/crear", tarjetaController::agregar);
        app.put("/tarjetas/actualizar", tarjetaController::actualizarTarjeta);
        app.delete("/tarjetas/eliminar/{id}", tarjetaController::eliminar);
    }
}
