package org.dasultra.file;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class FileManager implements Serializable {

    private static String f;
    private static File file;
    private static YamlConfiguration data;

    public FileManager(File file) {
        this.file = file;
        data = YamlConfiguration.loadConfiguration(file);
        f = file.getPath();
    }

    public FileManager(String f) {
        this.f = f;
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
    }

    public void set(String o, Object v) {
        data.set(o, v);
        save();
    }

    public void deleteLine(String o) {
        set(o, null);
        save();
    }

    public boolean delete() {
        file = new File(f);
        return file.delete();
    }

    public void add(String o, Object v) {
        if (!isSet(o)) {
            set(o, v);
        }
        save();
    }

    public boolean exist() {
        file = new File(f);
        return file.exists();
    }

    public boolean isSet(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.isSet(o);
    }

    public List<String> getStringList(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getStringList(o);
    }

    public boolean getBoolean(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getBoolean(o);
    }

    public UUID getUUID(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return UUID.fromString(Objects.requireNonNull(data.getString(o)));
    }

    public Location getLocation(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getLocation(o);
    }

    public int getInt(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getInt(o);
    }

    public double getDouble(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getDouble(o);
    }

    public long getLong(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getLong(o);
    }

    public Object get(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.get(o);
    }

    public List<?> getList(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getList(o);
    }

    public ItemStack getItemStack(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getItemStack(o);
    }

    public String getString(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getString(o);
    }

    public void save() {
        try {
            data.save(file);
        } catch (IOException ignored) {
        }
    }

}