package criv.personalponderings.sound;

import criv.personalponderings.PersonalPonderings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class Sounds {
    public static final SoundEvent SMALL_HURT = registerSoundEvent("small_hurt");
    public static final SoundEvent MEDIUM_HURT = registerSoundEvent("medium_hurt");
    public static final SoundEvent LARGE_HURT = registerSoundEvent("large_hurt");
    public static final SoundEvent FIRE_HURT = registerSoundEvent("fire_hurt");
    public static final SoundEvent POKE_HURT = registerSoundEvent("poke_hurt");
    public static final SoundEvent DEATH = registerSoundEvent("death");
    public static final SoundEvent SPAWN = registerSoundEvent("spawn");
    public static final SoundEvent MOB_HURT = registerSoundEvent("mob_hurt");
    public static final SoundEvent SKELETON_HURT = registerSoundEvent("skeleton_hurt");
    public static final SoundEvent PHANTOM_HURT = registerSoundEvent("phantom_hurt");
    public static final SoundEvent DROWN_HURT = registerSoundEvent("drown_hurt");
    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(PersonalPonderings.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static  void registerSounds() {
        PersonalPonderings.LOGGER.info("Registering sounds for " + PersonalPonderings.MOD_ID);
    }
}
