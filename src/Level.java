import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Level implements Serializable {
    private List<Block> blocks;

    public Level(int rowCount, int columnCount, int blockWidth, int blockHeight, int screenWidth, int screenHeight) {
        blocks = new ArrayList<>();
        int levelWidth = columnCount * (blockWidth + 5);
        int levelHeight = rowCount * (blockHeight + 5);
        int xOffset = (screenWidth - levelWidth) / 2;
        int yOffset = ((screenHeight - levelHeight) / 2 )-250;
        initializeLevel(rowCount, columnCount, blockWidth, blockHeight, screenWidth, screenHeight, xOffset, yOffset);
    }

    private void initializeLevel(int rowCount, int columnCount, int blockWidth, int blockHeight, int screenWidth, int screenHeight, int xOffset, int yOffset) {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                int x = xOffset + col * (blockWidth + 5);
                System.out.println(x);
                int y = yOffset + row * (blockHeight + 5);
                System.out.println(y);
                Block block = new Block(x, y, blockWidth, blockHeight);
                blocks.add(block);
            }
        }
    }

    public void generateNewLevel(int rowCount, int columnCount, int blockWidth, int blockHeight) {
        blocks.clear();

        for (int row = 0; row < rowCount / 2; row++) {
            int blocksInRow = row + 1;

            for (int col = 0; col < blocksInRow; col++) {
                int x = 50 + col * (blockWidth + 5) + (columnCount - blocksInRow) * (blockWidth + 5) / 2;
                int y = row * (blockHeight + 5) + 100;
                Block block = new Block(x, y, blockWidth, blockHeight);
                blocks.add(block);
            }
        }

        for (int row = rowCount / 2; row < rowCount; row++) {
            int blocksInRow = rowCount - row;

            for (int col = 0; col < blocksInRow; col++) {
                int x = 50 + col * (blockWidth + 5) + (columnCount - blocksInRow) * (blockWidth + 5) / 2;
                int y = row * (blockHeight + 5) + 100;
                Block block = new Block(x, y, blockWidth, blockHeight);
                blocks.add(block);
            }
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
