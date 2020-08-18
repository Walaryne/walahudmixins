package walaryne.walahudmixins.mixin;

import fi.dy.masa.minihud.config.InfoToggle;
import fi.dy.masa.minihud.event.RenderHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderHandler.class)
public class RenderHandlerMixin {
    @Inject(at = @At("HEAD"),
            method = "addLine(Lfi/dy/masa/minihud/config/InfoToggle;)V",
    remap = false)
    private void onAddLine(InfoToggle type, CallbackInfo ci) {
        System.out.println("Injection is functioning.");
    }
}
