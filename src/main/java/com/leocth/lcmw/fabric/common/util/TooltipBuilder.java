package com.leocth.lcmw.fabric.common.util;

import com.google.common.collect.ImmutableList;
import com.leocth.lcmw.fabric.common.LCMWFabric;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class TooltipBuilder {
    private final String namespace;
    private final String id;
    private final List<Text> tooltip;

    public TooltipBuilder(String namespace, String id) {
        this.namespace = namespace;
        this.id = id;
        this.tooltip = new ArrayList<>();
    }

    public TooltipBuilder(String id) {
        this(LCMWFabric.MODID, id);
    }

    public TooltipBuilder append(Text text) {
        tooltip.add(text);
        return this;
    }

    public TooltipBuilder composeAndAppend(String category, int lines) {
        for (int i = 1; i <= lines; i++) {
            tooltip.add(TextUtil.makeTranslatableText("tooltip", namespace, id, category + i));
        }
        return this;
    }

    public TooltipBuilder desc(int lines) {
        return composeAndAppend("desc", lines);
    }

    public TooltipBuilder witty(int lines) {
        return composeAndAppend("witty", lines);
    }

    public List<Text> construct() {
        return ImmutableList.copyOf(tooltip);
    }
}
