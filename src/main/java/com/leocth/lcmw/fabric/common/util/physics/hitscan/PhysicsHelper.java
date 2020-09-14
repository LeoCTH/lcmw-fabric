package com.leocth.lcmw.fabric.common.util.physics.hitscan;

import com.google.common.collect.Sets;
import com.leocth.lcmw.fabric.common.util.physics.MutableVec3d;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public final class PhysicsHelper {

    private PhysicsHelper() {}

    // TODO quarter steps & hitresult
    public static boolean calcHitscan(
            @NotNull World world,
            @NotNull Entity entity,
            HitscanArgs args,
            Consumer<Entity> onDamage
    ) {
        MutableVec3d vec = MutableVec3d.fromPolar(entity.pitch, entity.yaw);
        MutableVec3d pos = new MutableVec3d(vec).add(entity.getX(), entity.getEyeY(), entity.getZ());
        BlockPos.Mutable bp = new BlockPos.Mutable();

        Set<Entity> entities = Sets.newHashSet();
        int penetratedEntities = 0;

        top:
        for (int i = 0; i < args.maxRange && penetratedEntities <= args.permeability; i++) {
            bp.set(pos.getX(), pos.getY(), pos.getZ());
            BlockState block = world.getBlockState(bp);
            // todo use VoxelShape#contains
            if (!block.isAir()) {

            }

            //todo
            List<Entity> ent = world.getEntitiesByType(
                    null,
                    constructBox(pos),
                    (enti) -> args.entityFilter.test(enti) && enti.getBoundingBox().contains(pos.getX(), pos.getY(), pos.getZ())
            );
            if (!ent.isEmpty()) {
                int pen = Math.max(0, args.permeability - penetratedEntities);
                entities.addAll(ent.subList(0, pen + 1));
                penetratedEntities += pen;
            }
            pos.add(vec);
        }
        entities.forEach(onDamage);
        return !entities.isEmpty();
    }

    private static Box constructBox(MutableVec3d vec) {
        double x = vec.getX();
        double y = vec.getY();
        double z = vec.getZ();

        return new Box(x - 0.5, y - 0.5, z - 0.5, x + 0.5, y + 0.5, z + 0.5);
    }
}
