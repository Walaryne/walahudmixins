package walaryne.walahudmixins.mixin;

import fi.dy.masa.minihud.config.InfoToggle;
import fi.dy.masa.minihud.event.RenderHandler;
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

    @Inject(at = @At("TAIL"),
            method = "addLine(Lfi/dy/masa/minihud/config/InfoToggle;)V",
            remap = false)
    private void onAddLine(InfoToggle type, CallbackInfo ci) {
        if(this.addedTypes.contains(InfoToggle.COORDINATES)) {
            return;
        }
        this.addLine("This is a test from an injection");
    }
}
