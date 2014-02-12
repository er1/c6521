
import java.io.IOException;
import java.util.AbstractList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chanman
 */
public class RunSorter {

    BlockAccess read;
    BlockAccess write;
    Block[] blocks;
    int runSize;

    RunSorter(BlockAccess read, BlockAccess write, int rs) {
        this.read = read;
        this.write = write;
        runSize = rs;
        blocks = new Block[runSize];
    }

    private static class BlockArrayList extends AbstractList<Tuple> {

        Block[] block;
        int size;

        BlockArrayList(Block[] blocks, int len) {
            this.block = blocks;
            size = len;
        }

        @Override
        public Tuple get(int index) {
            return block[index / Block.TUPLES_PER_BLOCK].get(index % Block.TUPLES_PER_BLOCK);
        }

        @Override
        public Tuple set(int index, Tuple t) {
            return block[index / Block.TUPLES_PER_BLOCK].set(index % Block.TUPLES_PER_BLOCK, t);
        }

        @Override
        public int size() {
            return Block.TUPLES_PER_BLOCK * size;
        }
    }

    void run() throws IOException {
        int blockCount = read.getBlockCount();
        int numRuns = (blockCount + runSize - 1) / runSize;

        for (int i = 0; i < numRuns; i++) {
            int blk;
            for (blk = 0; blk < runSize && (i * runSize + blk) < blockCount; blk++) {
                blocks[blk] = new Block();
                read.read(i * runSize + blk, blocks[blk]);
            }

            Collections.sort(new BlockArrayList(blocks, blk));

            for (blk = 0; blk < runSize && (i * runSize + blk) < blockCount; blk++) {
                write.write(i * runSize + blk, blocks[blk]);
            }
        }
    }
}
