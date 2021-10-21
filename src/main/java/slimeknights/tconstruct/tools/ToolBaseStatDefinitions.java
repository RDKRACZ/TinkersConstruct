package slimeknights.tconstruct.tools;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.ToolBaseStatDefinition;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ToolBaseStatDefinitions {
  // pickaxes
  static final ToolBaseStatDefinition PICKAXE = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 0.5f) // gains +0.5 damage from tool piercing, hence being lower than vanilla
    .set(ToolStats.ATTACK_SPEED, 1.2f)
    .build();
  static final ToolBaseStatDefinition SLEDGE_HAMMER = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 3f) // gains +5 undead damage from smite modifier
    .set(ToolStats.ATTACK_SPEED, 0.75f)
    .modifier(ToolStats.ATTACK_DAMAGE, 1.35f)
    .modifier(ToolStats.MINING_SPEED, 0.4f)
    .modifier(ToolStats.DURABILITY, 4f)
    .setPrimaryHeadWeight(2).startingSlots(SlotType.UPGRADE, 2).build();
  static final ToolBaseStatDefinition VEIN_HAMMER = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 3f) // gains +1.25 damage from piercing
    .set(ToolStats.ATTACK_SPEED, 1.1f)
    .modifier(ToolStats.ATTACK_DAMAGE, 1.25f)
    .modifier(ToolStats.MINING_SPEED, 0.3f)
    .modifier(ToolStats.DURABILITY, 5.0f)
    .setPrimaryHeadWeight(2).startingSlots(SlotType.UPGRADE, 2).build();

  // shovels
  static final ToolBaseStatDefinition MATTOCK = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 1.5f)
    .set(ToolStats.ATTACK_SPEED, 1f)
    .build();
  static final ToolBaseStatDefinition EXCAVATOR = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 1.5f)
    .set(ToolStats.ATTACK_SPEED, 1.0f)
    .modifier(ToolStats.ATTACK_DAMAGE, 1.2f)
    .modifier(ToolStats.MINING_SPEED, 0.3f)
    .modifier(ToolStats.DURABILITY, 3.75f)
    .startingSlots(SlotType.UPGRADE, 2).build();

  // axes
  static final ToolBaseStatDefinition HAND_AXE = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 6.0f)
    .set(ToolStats.ATTACK_SPEED, 0.9f)
    .build();
  static final ToolBaseStatDefinition BROAD_AXE = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 5f)
    .set(ToolStats.ATTACK_SPEED, 0.6f)
    .modifier(ToolStats.ATTACK_DAMAGE, 1.5f)
    .modifier(ToolStats.MINING_SPEED, 0.3f)
    .modifier(ToolStats.DURABILITY, 4.25f)
    .setPrimaryHeadWeight(2).startingSlots(SlotType.UPGRADE, 2).build();

  // scythes
  static final ToolBaseStatDefinition KAMA = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 1f)
    .set(ToolStats.ATTACK_SPEED, 1.8f)
    .modifier(ToolStats.ATTACK_DAMAGE, 0.75f)
    .build();
  static final ToolBaseStatDefinition SCYTHE = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 1f)
    .set(ToolStats.ATTACK_SPEED, 0.8f)
    .modifier(ToolStats.MINING_SPEED, 0.45f)
    .modifier(ToolStats.DURABILITY, 2.5f)
    .startingSlots(SlotType.UPGRADE, 2).build();

  // swords
  static final ToolBaseStatDefinition DAGGER = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 2f)
    .modifier(ToolStats.ATTACK_DAMAGE, 0.5f)
    .set(ToolStats.ATTACK_SPEED, 2.0f)
    .modifier(ToolStats.MINING_SPEED, 0.75f)
    .modifier(ToolStats.DURABILITY, 0.75f).build();
  static final ToolBaseStatDefinition SWORD = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 3f)
    .set(ToolStats.ATTACK_SPEED, 1.6f)
    .modifier(ToolStats.MINING_SPEED, 0.5f)
    .modifier(ToolStats.DURABILITY, 1.1f).build();
  static final ToolBaseStatDefinition CLEAVER = ToolBaseStatDefinition.builder()
    .bonus(ToolStats.ATTACK_DAMAGE, 3.5f)
    .set(ToolStats.ATTACK_SPEED, 0.9f)
    .modifier(ToolStats.ATTACK_DAMAGE, 1.5f)
    .modifier(ToolStats.MINING_SPEED, 0.25f)
    .modifier(ToolStats.DURABILITY, 3.5f)
    .setPrimaryHeadWeight(2).startingSlots(SlotType.UPGRADE, 2).build();

  // special items
  static final ToolBaseStatDefinition FLINT_AND_BRONZE = ToolBaseStatDefinition.builder()
    .set(ToolStats.DURABILITY, 100)
    .startingSlots(SlotType.UPGRADE, 1)
    .startingSlots(SlotType.ABILITY, 0)
    .build();
}
