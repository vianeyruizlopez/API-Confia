package org.example.service;

import org.example.model.Tarjeta;
import org.example.repository.TarjetaRepository;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TarjetaService {
    private final TarjetaRepository repository = new TarjetaRepository();
    private final Map<String, Integer> CATEGORIAS = Map.of(
            "Ansiedad", 1,
            "Tristeza", 2,
            "Felicidad", 3,
            "Agradecida", 4
    );

    public List<Tarjeta> obtenerTodoTarjetas() {
        return repository.obtenerTodoTarjetas();
    }

    public List<Tarjeta> buscarPorCategoria(String nombre) {
        return repository.buscarPorCategoria(nombre);
    }

    public Tarjeta unaTarjetaAlAzar(String categoria) {
        List<Tarjeta> filtrados = repository.buscarPorCategoria(categoria);
        if (!filtrados.isEmpty()) {
            Collections.shuffle(filtrados);
            return filtrados.get(0);
        }
        return null;
    }

    public void agregarTarjeta(Tarjeta tarjeta) {
        if (tarjeta.getCategoriaId() == 0 && tarjeta.getNombreCategoria() != null) {
            Integer id = CATEGORIAS.get(tarjeta.getNombreCategoria());
            if (id != null) tarjeta.setCategoriaId(id);
        }
        repository.agregarTarjeta(tarjeta);
    }

    public void actualizarTarjeta(Tarjeta tarjeta) {
        repository.actualizarTarjeta(tarjeta);
    }

    public void eliminarTarjeta(int id) {
        repository.eliminarTarjeta(id);
    }
}
