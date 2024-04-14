package criv.personalponderings.mixin.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends AbstractClientPlayerEntity {
    public AbstractClientPlayerEntityMixin() {
        super(null, null);
    }

    @Inject(method = "damage", at = @At("HEAD"))
    public void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        //if(amount < 1) return;
        //this.sendMessage(Text.literal("youch " + amount + " damage and source is " + source + source.getAttacker()), false);
    }
}
