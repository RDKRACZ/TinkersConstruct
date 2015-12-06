package slimeknights.tconstruct.smeltery.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelFluid;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

import slimeknights.tconstruct.library.client.RenderUtil;
import slimeknights.tconstruct.library.smeltery.SmelteryTank;
import slimeknights.tconstruct.smeltery.tileentity.TileSmeltery;

public class SmelteryRenderer extends TileEntitySpecialRenderer<TileSmeltery> {

  @Override
  public void renderTileEntityAt(TileSmeltery smeltery, double x, double y, double z, float partialTicks, int destroyStage) {
    if(!smeltery.isActive())
      return;

    // get liquids
    SmelteryTank tank = smeltery.getTank();

    // safety first!
    if(tank == null || smeltery.minPos == null || smeltery.maxPos == null)
      return;

    List<FluidStack> fluids = tank.getFluids();

    // empty smeltery :(
    if(!fluids.isEmpty()) {
      // calculate x/z parameters. they'll be the same for all liquids
      double x1 = smeltery.minPos.getX() - smeltery.getPos().getX();
      double y1 = smeltery.minPos.getY() - smeltery.getPos().getY();
      double z1 = smeltery.minPos.getZ() - smeltery.getPos().getZ();

      double x2 = smeltery.maxPos.getX() - smeltery.getPos().getX();
      double z2 = smeltery.maxPos.getZ() - smeltery.getPos().getZ();

      BlockPos minPos = new BlockPos(x1, y1, z1);
      BlockPos maxPos = new BlockPos(x2, y1, z2);

      // calc heights, we use mB capacities and then convert it over to blockheights during rendering
      int yd = 1 + smeltery.maxPos.getY() - smeltery.minPos.getY();
      // one block height = 1000 mb
      int[] heights = calcLiquidHeights(fluids, tank.getMaxCapacity() - (int)(RenderUtil.FLUID_OFFSET*2000d), yd * 1000, 100);

      double curY = RenderUtil.FLUID_OFFSET;
      // rendering time
      for(int i = 0; i < fluids.size(); i++) {
        double h = (double) heights[i] / 1000d;
        // minpos as start instead of smeltery.pos because we want to use the lighting inside the smeltery
        RenderUtil.renderStackedFluidCuboid(fluids.get(i), x,y,z, smeltery.minPos, minPos, maxPos, curY, curY+h);
        curY += h;
      }
    }


  }

  /**
   * calculate the rendering heights for all the liquids
   * @param liquids   The liquids
   * @param capacity  Max capacity of smeltery, to calculate how much height one liquid takes up
   * @param height    Maximum height, basically represents how much height full capacity is
   * @param min       Minimum amount of height for a fluid. A fluid can never have less than this value height returned
   * @return Array with heights corresponding to input-list liquids
   */
  public static int[] calcLiquidHeights (List<FluidStack> liquids, int capacity, int height, int min)
  {
    int fluidHeights[] = new int[liquids.size()];

    for (int i = 0; i < liquids.size(); i++)
    {
      FluidStack liquid = liquids.get(i);

      float h = (float) liquid.amount / (float) capacity;
      fluidHeights[i] = Math.max(min, (int) Math.ceil(h * (float)height));
    }

    // check if we have enough height to render everything
    int sum = 0;
    do
    {
      sum = 0;
      int biggest = -1;
      int m = 0;
      for (int i = 0; i < fluidHeights.length; i++)
      {
        sum += fluidHeights[i];
        if (liquids.get(i).amount > biggest)
        {
          biggest = liquids.get(i).amount;
          m = i;
        }
      }

      // remove a pixel from the biggest one
      if (sum > height)
        fluidHeights[m]--;
    } while (sum > height);

    return fluidHeights;
  }
}
