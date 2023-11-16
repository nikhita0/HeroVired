public class MoveZeros {
    public void moveZeroes(int[] nums) {
        int leftmostZeroIndex = 0; // Pointer for the leftmost zero

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // Check if the current element is non-zero
            if (nums[i] != 0) {
                // Swap non-zero element with the leftmost zero
                int temp = nums[i];
                nums[i] = nums[leftmostZeroIndex];
                nums[leftmostZeroIndex] = temp;

                // Move the leftmost zero pointer to the next position
                leftmostZeroIndex++;
            }
        }
    }

    public static void main(String[] args) {
        MoveZeros solution = new MoveZeros();
        int[] nums = {0, 1, 0, 3, 12};

        System.out.println("Original Array:");
        printArray(nums);

        solution.moveZeroes(nums);

        System.out.println("\nArray after moving zeros:");
        printArray(nums);
    }

    // Utility method to print the array
    private static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
