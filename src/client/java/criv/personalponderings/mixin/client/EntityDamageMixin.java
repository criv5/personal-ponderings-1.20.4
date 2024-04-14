package criv.personalponderings.mixin.client;

import criv.personalponderings.SoundEvents;
import criv.personalponderings.sound.Sounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
    public class EntityDamageMixin {

        @Shadow
        private ClientWorld world;

        @Inject(method = "onEntityDamage", at = @At("TAIL"))
        public void onEntityDamage(EntityDamageS2CPacket packet, CallbackInfo ci) {
            MinecraftClient client = MinecraftClient.getInstance();
            Entity target = world.getEntityById(packet.entityId());
            Entity source = world.getEntityById(packet.sourceCauseId());
            if (target != null && source != null) {
                SoundEvents.entityDamage(source, target, client);
            }
        }
    }
