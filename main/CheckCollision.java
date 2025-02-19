package main;
import entity.Entity;

public class CheckCollision {
    GamePanel gp;

    // Construtor correto
    public CheckCollision(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        // Calcula as posições de colisão da entidade com base em sua direção
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
    
        // Colunas e linhas para a verificação das tiles
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
    
        // Atributo para armazenar a verificação de colisão
        int[] tileNums = new int[2];  // Para as duas colunas (esquerda e direita)
    
        // Verificação de colisão dependendo da direção
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNums[0] = getTileNum(entityLeftCol, entityTopRow);
                tileNums[1] = getTileNum(entityRightCol, entityTopRow);
                break;
    
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNums[0] = getTileNum(entityLeftCol, entityBottomRow);
                tileNums[1] = getTileNum(entityRightCol, entityBottomRow);
                break;
    
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNums[0] = getTileNum(entityLeftCol, entityTopRow);
                tileNums[1] = getTileNum(entityLeftCol, entityBottomRow);
                break;
    
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNums[0] = getTileNum(entityRightCol, entityTopRow);
                tileNums[1] = getTileNum(entityRightCol, entityBottomRow);
                break;
    
            default:
                break;
        }
    
        // Verificação de colisão em ambos os tiles
        if (isCollision(tileNums[0]) || isCollision(tileNums[1])) {
            entity.collisionOn = true;
        }
    
    }
    
    private int getTileNum(int col, int row) {
        if (col >= 0 && col < gp.tileM.mapTileNum.length &&
            row >= 0 && row < gp.tileM.mapTileNum[0].length) {
            return gp.tileM.mapTileNum[col][row];
        } else {
            return -1;  // Valor inválido para evitar erro
        }
    }
    
    private boolean isCollision(int tileNum) {
        return tileNum != -1 && gp.tileM.tile[tileNum].collision;
    }
    

}
