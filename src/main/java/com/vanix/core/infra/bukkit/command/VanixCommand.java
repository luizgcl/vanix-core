package com.vanix.core.infra.bukkit.command;

import com.vanix.core.helpers.ClassGetter;
import com.vanix.core.helpers.Util;
import com.vanix.core.infra.bukkit.BukkitMain;

import java.util.logging.Logger;

public abstract class VanixCommand {

    public abstract void setup();

    public static int count = 0;

    public static void register() {
        register("com.vanix.core.infra.bukkit");
    }

    public static void register(String pkge) {

        long millis = System.currentTimeMillis();

        BukkitMain.getInstance().getLogger().info("Iniciando registro de comandos...");
        for (Class<?> classes : ClassGetter.getClassesForPackage(BukkitMain.class, pkge)) {
            if (VanixCommand.class.isAssignableFrom(classes) && classes != VanixCommand.class) {
                try {
                    VanixCommand command = (VanixCommand) classes.newInstance();
                    command.setup();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    BukkitMain.getInstance().getLogger().severe("Ocorreu algum erro ao registrar o comando '" + classes.getName() + "'.");
                }
            }
        }

        if (count > 0)
            BukkitMain.getInstance().getLogger().info("Registro de comandos concluido (Total de comandos registrados: " + count + ") em "
                    + Util.formatMillis(millis));
    }

}
