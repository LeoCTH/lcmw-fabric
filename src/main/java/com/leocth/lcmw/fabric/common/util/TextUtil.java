package com.leocth.lcmw.fabric.common.util;

import com.leocth.lcmw.fabric.common.LCMWFabric;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;

import java.util.List;

public final class TextUtil {

    private TextUtil() {}

    public static String composeString(String first, String... pieces) {
        StringBuilder keyBuilder = new StringBuilder(first);
        for (int i = 0; i < pieces.length; i++) {
            keyBuilder.append('.');
            keyBuilder.append(pieces[i]);
        }
        return keyBuilder.toString();
    }

    public static TranslatableText makeTranslatableText(String category, String namespace, String... pieces) {
        String[] args = new String[pieces.length + 1];
        args[0] = namespace;
        System.arraycopy(pieces, 0, args, 1, pieces.length);
        return new TranslatableText(composeString(category, args));
    }

    public static TranslatableText makeTranslatableTextDefaultedId(String category, String... pieces) {
        return makeTranslatableText(category, LCMWFabric.MODID, pieces);
    }

    public static void appendTooltips(List<Text> list, String id, String category, int lines) {
        for (int i = 0; i < lines; i++) {
            list.add(makeTranslatableTextDefaultedId("tooltip", id, category + (i + 1)));
        }
    }

    public static void appendDescTooltips(List<Text> list, Item item, int lines) {
        appendTooltips(list, Registry.ITEM.getId(item).getPath(), "desc", lines);
    }
    public static void appendWittyTooltips(List<Text> list, Item item, int lines) {
        appendTooltips(list, Registry.ITEM.getId(item).getPath(), "witty", lines);
    }
}
