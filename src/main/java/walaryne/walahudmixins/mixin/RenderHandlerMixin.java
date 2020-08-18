package walaryne.walahudmixins.mixin;

import fi.dy.masa.minihud.config.InfoToggle;
import fi.dy.masa.minihud.event.RenderHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(RenderHandler.class)
public abstract class RenderHandlerMixin {
    @Shadow(remap = false) protected abstract void addLine(String text);

    @Shadow(remap = false) private Set<InfoToggle> addedTypes;

    @Final
    @Shadow(remap = false)
    private MinecraftClient mc = MinecraftClient.getInstance();

    @Inject(at = @At("TAIL"),
            method = "addLine(Lfi/dy/masa/minihud/config/InfoToggle;)V",
            remap = false)
    private void onAddLine(InfoToggle type, CallbackInfo ci) {
        Entity entity = mc.getCameraEntity();
        double y = entity.getBoundingBox().minY;
        if(this.addedTypes.contains(InfoToggle.COORDINATES)) {
            return;
        }

        if(InfoToggle.COORDINATES.getBooleanValue()) {
            RegistryKey<DimensionType> dimension = mc.world.getDimensionRegistryKey();
            if (dimension == DimensionType.THE_NETHER_REGISTRY_KEY) {
                this.addLine(String.format("XZ-OW: %.2f / %.2f", entity.getX() * 8, entity.getZ() * 8));
            } else if (dimension == DimensionType.OVERWORLD_REGISTRY_KEY) {
                this.addLine(String.format("XZ-NE: %.2f / %.2f", entity.getX() / 8, entity.getZ() / 8));
            }
        }
    }
}
