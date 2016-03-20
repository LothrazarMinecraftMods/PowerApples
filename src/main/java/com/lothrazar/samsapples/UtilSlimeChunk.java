package com.lothrazar.samsapples;

import java.util.Random;
 
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class UtilSlimeChunk
{
	public static boolean isSlimeChunk(World world, BlockPos pos)
	{
		if(world == null){return false;}//shouldnt happen anymore since i check worldServers.length above now
		long seed =  world.getSeed();
		if(seed == 0){return false;}//on a server where seed is hidden
		
		Chunk in = world.getChunkFromBlockCoords(pos);

		//formula source : http://minecraft.gamepedia.com/Slime
		Random rnd = new Random(seed +
		        (long) (in.xPosition * in.xPosition * 0x4c1906) +
		        (long) (in.xPosition * 0x5ac0db) + 
		        (long) (in.zPosition * in.zPosition) * 0x4307a7L +
		        (long) (in.zPosition * 0x5f24f) ^ 0x3ad8025f);
		
		boolean isSlimeChunk = (rnd.nextInt(10) == 0);
    
		return isSlimeChunk;
	}
}
