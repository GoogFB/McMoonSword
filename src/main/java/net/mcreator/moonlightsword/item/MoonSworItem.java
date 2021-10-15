
package net.mcreator.moonlightsword.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.entity.player.PlayerEntity;

import net.mcreator.moonlightsword.procedures.MoonSwordRightClickedInAirProcedure;
import net.mcreator.moonlightsword.MoonlightswordModElements;

import java.util.Map;
import java.util.HashMap;

@MoonlightswordModElements.ModElement.Tag
public class MoonSworItem extends MoonlightswordModElements.ModElement {
	@ObjectHolder("moonlightsword:moon_swor")
	public static final Item block = null;
	public MoonSworItem(MoonlightswordModElements instance) {
		super(instance, 3);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 250;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 26f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Items.NETHER_STAR));
			}
		}, 3, 0f, new Item.Properties().group(ItemGroup.COMBAT).isImmuneToFire()) {
			@Override
			public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
				ActionResult<ItemStack> retval = super.onItemRightClick(world, entity, hand);
				ItemStack itemstack = retval.getResult();
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					MoonSwordRightClickedInAirProcedure.executeProcedure($_dependencies);
				}
				return retval;
			}

			@Override
			@OnlyIn(Dist.CLIENT)
			public boolean hasEffect(ItemStack itemstack) {
				return true;
			}
		}.setRegistryName("moon_swor"));
	}
}
