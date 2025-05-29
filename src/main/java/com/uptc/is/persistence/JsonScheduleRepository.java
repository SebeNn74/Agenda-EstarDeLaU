package com.uptc.is.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.uptc.is.model.domain.Schedule;
import com.uptc.is.model.repository.ScheduleRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JsonScheduleRepository implements ScheduleRepository {

    private final String filePath;
    private final JsonService jsonService;
    private final List<Schedule> schedulesCache;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public JsonScheduleRepository(String filePath, JsonService jsonService) {
        this.filePath = filePath;
        this.jsonService = jsonService;
        this.schedulesCache = loadData();
    }

    private List<Schedule> loadData() {
        lock.readLock().lock();
        try {
            return jsonService.readListFromFile(filePath, new TypeReference<List<Schedule>>() {});
        } catch (IOException e) {
            System.err.println("Error crítico al cargar horarios desde JSON (" + filePath + "): " + e.getMessage());
            // Lanzar excepción
            return new ArrayList<>();
        } finally {
            lock.readLock().unlock();
        }
    }

    private void saveData() {
        lock.writeLock().lock();
        try {
            jsonService.writeListToFile(filePath, schedulesCache);
        } catch (IOException e) {
            System.err.println("Error crítico al guardar horarios en JSON (" + filePath + "): " + e.getMessage());
            // Lanzar excepción
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void create(Schedule schedule) {
        lock.writeLock().lock();
        try {
            jsonService.writeListToFile(filePath, schedulesCache);
        } catch (IOException e) {
            System.err.println("Error crítico al guardar cajeros en JSON (" + filePath + "): " + e.getMessage());
            // Lanzar excepción
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<Schedule> searchById(String id) {
        lock.readLock().lock();
        try {
            return schedulesCache.stream()
                    .filter(s -> s.getID().equals(id))
                    .findFirst();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Schedule> getAll() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(schedulesCache); //Se devuelve una copia
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Schedule> getAllById(String cashierId) {
        lock.readLock().lock();
        try {
            List<Schedule> shedulesFounded = schedulesCache.stream()
                    .filter(s -> s.getCashier().equals(cashierId)).toList();
            return new ArrayList<>(shedulesFounded);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void update(Schedule schedule) {
        lock.writeLock().lock();
        try {
            Optional<Schedule> sheduleFounded = schedulesCache.stream()
                    .filter(s -> s.getID().equals(schedule.getID()))
                    .findFirst();

            if (sheduleFounded.isPresent()) {
                int index = schedulesCache.indexOf(sheduleFounded.get());
                schedulesCache.set(index, schedule);
                saveData();
            } else {
                System.err.println("Intento de actualizar horario no existente con ID: " + schedule.getID());
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void remove(String id) {
        lock.writeLock().lock();
        try {
            boolean removed = schedulesCache.removeIf(s -> s.getID().equals(id));
            if (removed) {
                saveData();
            } else {
                System.err.println("Intento de eliminar horario no existente con ID: " + id);
                //Lanzar excepción
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
}
