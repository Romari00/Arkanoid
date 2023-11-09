import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Level implements Serializable {
    private List<Block> blocks;

    public Level() {
        blocks = new ArrayList<>();
        initializeLevel();
    }

    private void initializeLevel() {
        int rowCount = 5;
        int columnCount = 5;//123
        int blockWidth = 60;
        int blockHeight = 20;

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                int x = col * (blockWidth + 5);
                int y = row * (blockHeight + 5);
                Block block = new Block(x, y, blockWidth, blockHeight);
                blocks.add(block);
            }
        }
    }
    public void NewLev() {
        int rowCount = 10;
        int columnCount = 10;
        int blockWidth = 60;
        int blockHeight = 20;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                int x = col * (blockWidth + 5);
                int y = row * (blockHeight + 5);
                Block block = new Block(x, y, blockWidth, blockHeight);
                blocks.add(block);
            }
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
