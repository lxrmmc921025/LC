package string.array;

/**
 * Created by az on 9/23/2019.
 */
public class RangeSumQuery2D {
    public int[][] matrix;
    public RangeSumQuery2D(int[][] matrix) {
        //[!!]first row & col also need to be preprocessed
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = matrix[i - 1][0] + matrix[i][0];
        }
        for (int j = 1; j < matrix[0].length; j++) {
            matrix[0][j] = matrix[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1] - matrix[i - 1][j - 1] + matrix[i][j];
            }
        }
        //[!!]use "this" key word to find the class defined variable
        this.matrix = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        //[!!]corner case: row or col == 0
        if (row1 == 0 && col1 == 0) return matrix[row2][col2];
        if (row1 == 0) return matrix[row2][col2] - matrix[row2][col1 - 1];
        if (col1 == 0) return matrix[row2][col2] - matrix[row1 - 1][col2];
        return matrix[row2][col2] + matrix[row1 - 1][col1 - 1] - matrix[row1 - 1][col2] - matrix[row2][col1 - 1];
    }
}
