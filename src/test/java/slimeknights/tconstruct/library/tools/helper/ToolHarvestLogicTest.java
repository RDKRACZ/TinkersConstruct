package slimeknights.tconstruct.library.tools.helper;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolType;
import org.junit.jupiter.api.Test;
import slimeknights.tconstruct.fixture.MaterialItemFixture;
import slimeknights.tconstruct.fixture.MaterialStatsFixture;
import slimeknights.tconstruct.fixture.ToolDefinitionFixture;
import slimeknights.tconstruct.library.tools.ToolBaseStatDefinition;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.item.ToolItemTest;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.item.small.HarvestTool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class ToolHarvestLogicTest extends ToolItemTest {

  private final HarvestTool pickaxeTool = new HarvestTool(
    new Item.Properties().addToolType(ToolType.PICKAXE, 1),
    ToolDefinitionFixture.getStandardToolDefinition());
  private final ToolHarvestLogic toolHarvestLogic = new ToolHarvestLogic();

  @Test
  void calcSpeed_dirt_notEffective() {
    ItemStack tool = buildTestTool(pickaxeTool);

    float speed = toolHarvestLogic.getDestroySpeed(tool, Blocks.DIRT.getDefaultState());

    assertThat(speed).isEqualTo(1f);
  }

  @Test
  void calcSpeed_cobble_effective() {
    ItemStack tool = buildTestTool(pickaxeTool);

    float speed = toolHarvestLogic.getDestroySpeed(tool, Blocks.COBBLESTONE.getDefaultState());

    assertThat(speed).isEqualTo(MaterialStatsFixture.MATERIAL_STATS_HEAD.getMiningSpeed());
  }

  @Test
  void calcSpeed_obsidian_notEnoughHarvestLevel() {
    ItemStack tool = buildTestTool(pickaxeTool);

    float speed = toolHarvestLogic.getDestroySpeed(tool, Blocks.OBSIDIAN.getDefaultState());

    assertThat(speed).isEqualTo(1f);
  }

  @Test
  void calcSpeed_broken_slowButNotZero() {
    ItemStack tool = buildTestTool(pickaxeTool);
    breakTool(tool);

    float speed = toolHarvestLogic.getDestroySpeed(tool, Blocks.DIRT.getDefaultState());

    assertThat(speed).isLessThan(1f);
    assertThat(speed).isGreaterThan(0f);
  }

  @Test
  void calcSpeed_effective_withMiningModifier() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    // need to initalize the tool types for blocks, so we show as effective
    Method method = ForgeHooks.class.getDeclaredMethod("initTools");
    method.setAccessible(true);
    method.invoke(null);

    float modifier = 2f;
    IModifiable toolWithMiningModifier = new HarvestTool(
      new Item.Properties().addToolType(ToolType.PICKAXE, 1),
      new ToolDefinition(
        ToolBaseStatDefinition.builder().modifier(ToolStats.MINING_SPEED, modifier).build(),
        () -> ImmutableList.of(MaterialItemFixture.MATERIAL_ITEM_HEAD, MaterialItemFixture.MATERIAL_ITEM_HANDLE, MaterialItemFixture.MATERIAL_ITEM_EXTRA),
        Collections::emptyList
      ));
    ItemStack tool = buildTestTool(toolWithMiningModifier);

    float speed = toolHarvestLogic.getDestroySpeed(tool, Blocks.COBBLESTONE.getDefaultState());

    assertThat(speed).isEqualTo(MaterialStatsFixture.MATERIAL_STATS_HEAD.getMiningSpeed() * modifier);
  }
}
