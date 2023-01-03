package com.vanix.core.infra.bukkit.listeners.loader;

import com.vanix.core.helpers.ClassGetter;
import com.vanix.core.helpers.Util;
import com.vanix.core.infra.bukkit.BukkitMain;
import com.vanix.core.infra.bukkit.plugin.VanixPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.lang.reflect.Method;

public class VanixListener {

    public static void register() {
        register(BukkitMain.getInstance(), "com.vanix.core.infra.bukkit");
    }

    public static void register(VanixPlugin plugin, String pkge) {
        int count = 0;
        long millis = System.currentTimeMillis();

        plugin.getLogger().info("Registrando eventos..");
        for (Class<?> classes : ClassGetter.getClassesForPackage(plugin.getClass(), pkge)) {
            if (Listener.class.isAssignableFrom(classes)) {
                try {
                    Listener listener = (Listener) classes.newInstance();
                    plugin.registerListeners(listener);

                    for (Method method : listener.getClass().getDeclaredMethods()) {
                        if (method.getAnnotation(EventHandler.class) != null) {
                            count += 1;
                        }
                    }
                } catch (InstantiationException | IllegalAccessException  e) {
                    plugin.getLogger().severe("Ocorreu um erro ao registrar eventos da classe " + classes.getSimpleName());
                    plugin.getLogger().severe("> Causa do erro " + e.getCause());
                }
            }
        }

        if (count > 0)
            plugin.getLogger().info("Registro de eventos concluidos (Total de eventos registrados: " + count + ") em "
                    + Util.formatMillis(millis));
    }

}
