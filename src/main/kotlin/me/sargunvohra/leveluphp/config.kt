package me.sargunvohra.leveluphp

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.client.IModGuiFactory
import net.shadowfacts.config.Config
import net.shadowfacts.config.ConfigManager
import net.shadowfacts.shadowmc.config.GUIConfig
import java.io.File

@Config(name = MOD_ID)
object LuhpConfig {

    @JvmStatic
    lateinit var config: Configuration

    @JvmStatic @Config.Prop(description = "Starting HP offset in half-hearts.")
    var hpOffset = 0

    @JvmStatic @Config.Prop(description = "Maximum HP level.")
    var maximumLevel = 10

    @JvmStatic @Config.Prop(description = "HP gain per level in half-hearts")
    var hpPerLevel = 2

    @JvmStatic @Config.Prop(description = "Base HP-XP needed to advance to next HP level (base + scale*level).")
    var neededXpBase = 500

    @JvmStatic @Config.Prop(description = "Scaling HP-XP needed to advance to next HP level (base + scale*level).")
    var neededXpScale = 50

    @JvmStatic @Config.Prop(description = "Base HP-XP lost on death (base + scale*level).")
    var deathXpPenaltyBase = 100

    @JvmStatic @Config.Prop(description = "Scaling HP-XP lost on death (base + scale*level).")
    var deathXpPenaltyScale = 10

    @JvmStatic @Config.Prop(description = "Whether the player should heal when HP is leveled up")
    var healOnLevelUp = true

    @JvmStatic @Config.Prop(description = "Whether the player's HP level and XP should reset on death")
    var resetOnDeath = false

    @JvmStatic @Config.Prop(description = "XP gained from killing monsters")
    var monsterGain = 5

    @JvmStatic @Config.Prop(description = "XP gained from killing livestock")
    var livestockGain = 2

    fun init(configFile: File) {
        config = Configuration(configFile)
    }

    fun load() {
        ConfigManager.load(LuhpConfig::class.java, Configuration::class.java, config)
        if (config.hasChanged())
            config.save()
    }
}

class LevelUpHpConfigGui(parent: GuiScreen) : GUIConfig(parent, MOD_ID, LuhpConfig.config)

@Suppress("unused")
class LevelUpHpConfigGuiFactory : IModGuiFactory {

    override fun initialize(minecraftInstance: Minecraft) {}

    override fun hasConfigGui() = true

    override fun createConfigGui(parentScreen: GuiScreen) = LevelUpHpConfigGui(parentScreen)

    override fun runtimeGuiCategories() = emptySet<IModGuiFactory.RuntimeOptionCategoryElement>()

}