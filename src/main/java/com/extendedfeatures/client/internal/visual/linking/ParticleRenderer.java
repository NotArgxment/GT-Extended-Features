package com.extendedfeatures.client.internal.visual.linking;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public class ParticleRenderer {

    private static final double particlesPerBlock = 2.5;

    public static Vec3 faceCenterTowards(BlockPos fromBlock, BlockPos toBlock) {
        Vec3 fromCenter = Vec3.atCenterOf(fromBlock);
        Vec3 toCenter = Vec3.atCenterOf(toBlock);
        Vec3 direction = toCenter.subtract(fromCenter);

        double axisX = Math.abs(direction.x);
        double axisY = Math.abs(direction.y);
        double axisZ = Math.abs(direction.z);

        if (axisX >= axisY && axisX >= axisZ) {
            return fromCenter.add(Math.signum(direction.x) * 0.5, 0, 0);
        } else if (axisY >= axisX && axisY >= axisZ) {
            return fromCenter.add(0, Math.signum(direction.y) * 0.5, 0);
        } else {
            return fromCenter.add(0, 0, Math.signum(direction.z) * 0.5);
        }
    }

    // Draws a single straight line of particles between two points
    public static void emitLine(ServerLevel level, Vec3 start, Vec3 end, ParticleOptions particle) {
        emitLine(level, start, end, particle, particlesPerBlock);
    }

    // Gets the level, where to start, where it ends, particle getType(), and takes the PPB
    public static void emitLine(ServerLevel level, Vec3 start, Vec3 end, ParticleOptions particle, double particlesPerBlock) {
        double distance = start.distanceTo(end);
        int steps = Math.max(1, (int) (distance * particlesPerBlock));
        for (int i = 0; i <= steps; i++) {
            double t = (double) i / steps;
            Vec3 point = start.lerp(end, t);
            level.sendParticles(particle, point.x, point.y, point.z, 1, 0, 0, 0, 0);
        }
    }
}