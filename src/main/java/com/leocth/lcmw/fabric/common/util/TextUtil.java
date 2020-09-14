package com.leocth.lcmw.fabric.common.util;

import com.leocth.lcmw.fabric.common.LCMWFabric;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;

import java.util.List;

public final class TextUtil {

    private TextUtil() {}

    public static String composeString(String category, String... pieces) {
        StringBuilder keyBuilder = new StringBuilder(category);
        keyBuilder.append('.');
        keyBuilder.append(LCMWFabric.MODID);
        keyBuilder.append('.');
        for (int i = 0; i < pieces.length; i++) {
            keyBuilder.append(pieces[i]);
            if (i < pieces.length - 1)
                keyBuilder.append('.');
        }
        return keyBuilder.toString();
    }

    public static TranslatableText makeTranslatableText(String category, String... pieces) {
        return new TranslatableText(composeString(category, pieces));
    }

    public static void appendTooltips(List<Text> list, String id, String category, int lines) {
        for (int i = 0; i < lines; i++) {
            list.add(makeTranslatableText("tooltip", id, category + (i + 1)));
        }
    }

    public static void appendDescTooltips(List<Text> list, Item item, int lines) {
        appendTooltips(list, Registry.ITEM.getId(item).getPath(), "desc", lines);
    }
    public static void appendWittyTooltips(List<Text> list, Item item, int lines) {
        appendTooltips(list, Registry.ITEM.getId(item).getPath(), "witty", lines);
    }
}
