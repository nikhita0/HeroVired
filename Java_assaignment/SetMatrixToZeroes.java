public class SetMatrixToZeroes {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Step 1: Check if the first row or first column needs to be zeroed
        boolean zeroFirstRow = false;
        boolean zeroFirstCol = false;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                zeroFirstCol = true;
                break;
            }
        }

        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) {
                zeroFirstRow = true;
                break;
            }
        }

        // Step 2: Use the first row and first column to track zeroing information
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // Use the first column to track zero columns
                    matrix[0][j] = 0; // Use the first row to track zero rows
                }
            }
        }

        // Step 3: Set elements in identified rows and columns to zero
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 4: Zero the first row and first column if needed
        if (zeroFirstRow) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }

        if (zeroFirstCol) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        SetMatrixToZeroes solution = new SetMatrixToZeroes();
        int[][] matrix = {
            {1, 2, 3},
            {4, 0, 6},
            {7, 8, 9}
        };

        System.out.println("\nMatrix before setting zeroes:\n");
        displayMatrix(matrix);

        solution.setZeroes(matrix);

        System.out.println("\nMatrix after setting zeroes:\n");
        displayMatrix(matrix);
    }

    // Utility method to print the matrix
    private static void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
