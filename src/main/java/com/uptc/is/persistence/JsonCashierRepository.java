package com.uptc.is.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.repository.CashierRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JsonCashierRepository implements CashierRepository {

    private final String filePath;
    private final JsonService jsonService;
    private final List<Cashier> cashiersCache;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public JsonCashierRepository(String filePath, JsonService jsonService) {
        this.filePath = filePath;
        this.jsonService = jsonService;
        this.cashiersCache = loadData();
    }

    private List<Cashier> loadData() {
        lock.readLock().lock();
        try {
            return jsonService.readListFromFile(filePath, new TypeReference<List<Cashier>>() {});
        } catch (IOException e) {
            System.err.println("Error crítico al cargar cajeros desde JSON (" + filePath + "): " + e.getMessage());
            // Lanzar excepción
            return new ArrayList<>();
        } finally {
            lock.readLock().unlock();
        }
    }

    private void saveData() {
        lock.writeLock().lock();
        try {
            jsonService.writeListToFile(filePath, cashiersCache);
        } catch (IOException e) {
            System.err.println("Error crítico al guardar cajeros en JSON (" + filePath + "): " + e.getMessage());
            // Lanzar excepción
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void create(Cashier cashier) {
        lock.writeLock().lock();
        try {
            jsonService.writeListToFile(filePath, cashiersCache);
        } catch (IOException e) {
            System.err.println("Error crítico al guardar cajeros en JSON (" + filePath + "): " + e.getMessage());
            // Lanzar excepción
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<Cashier> searchById(String id) {
        lock.readLock().lock();
        try {
            return cashiersCache.stream()
                    .filter(c -> c.getNuip().equals(id))
                    .findFirst();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Cashier> getAll() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(cashiersCache); //Se devuelve una copia
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void update(Cashier cashier) {
        lock.writeLock().lock();
        try {
            Optional<Cashier> cashierFounded = cashiersCache.stream()
                    .filter(c -> c.getNuip().equals(cashier.getNuip()))
                    .findFirst();

            if (cashierFounded.isPresent()) {
                int index = cashiersCache.indexOf(cashierFounded.get());
                cashiersCache.set(index, cashier);
                saveData();
            } else {
                System.err.println("Intento de actualizar empleado no existente con ID: " + cashier.getNuip());
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void remove(String id) {
        lock.writeLock().lock();
        try {
            boolean removed = cashiersCache.removeIf(c -> c.getNuip().equals(id));
            if (removed) {
                saveData();
            } else {
                System.err.println("Intento de eliminar empleado no existente con ID: " + id);
                //Lanzar excepción
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
}
