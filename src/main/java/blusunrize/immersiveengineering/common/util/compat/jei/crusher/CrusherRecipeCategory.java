package blusunrize.immersiveengineering.common.util.compat.jei.crusher;

import blusunrize.immersiveengineering.api.crafting.CrusherRecipe;
import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.metal.BlockTypes_MetalMultiblock;
import blusunrize.immersiveengineering.common.util.compat.jei.IERecipeCategory;
import blusunrize.immersiveengineering.common.util.compat.jei.JEIHelper;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class CrusherRecipeCategory extends IERecipeCategory<CrusherRecipe, CrusherRecipeWrapper>
{
	static ItemStack crusherStack;
	public CrusherRecipeCategory(IGuiHelper helper)
	{
		super("crusher","tile.immersiveengineering.metalMultiblock.crusher.name", helper.createBlankDrawable(140,50), CrusherRecipe.class, new ItemStack(IEContent.blockMetalMultiblock,1,BlockTypes_MetalMultiblock.CRUSHER.getMeta()));
		crusherStack = new ItemStack(IEContent.blockMetalMultiblock,1, BlockTypes_MetalMultiblock.CRUSHER.getMeta());
	}

	int[][] outputSlots;
	@Override
	@Deprecated
	public void setRecipe(IRecipeLayout recipeLayout, CrusherRecipeWrapper recipeWrapper)
	{
		//Deprecated
	}
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CrusherRecipeWrapper recipeWrapper, IIngredients ingredients)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 20, 3);
		guiItemStacks.init(1, false, 102, 3);
		outputSlots = new int[recipeWrapper.recipeOutputs.length][];
		guiItemStacks.set(0, recipeWrapper.getItemIn());
		guiItemStacks.set(1, recipeWrapper.recipeOutputs[0]);
		outputSlots[0] = new int[]{102,3};
		for(int i=1; i<recipeWrapper.recipeOutputs.length; i++)
		{
			outputSlots[i] = new int[]{102+(i-1)%2*18,21};
			guiItemStacks.init(i+1, false, outputSlots[i][0],outputSlots[i][1]);
			guiItemStacks.set(i+1, recipeWrapper.recipeOutputs[i]);
		}
	}

	@Override
	public void drawExtras(Minecraft minecraft)
	{
		JEIHelper.slotDrawable.draw(minecraft, 102,3);
		JEIHelper.slotDrawable.draw(minecraft, 20, 3);
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(CrusherRecipe recipe)
	{
		return new CrusherRecipeWrapper(recipe);
	}
}