package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {
		
// METHOD #1: calculateColumnTotal()
	
	// Test for adding 2 positive values 
    @Test
    public void calculateColumnTotalForTwoPositiveValues() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(7.5));
            oneOf(values).getValue(1, 0);
            will(returnValue(2.5));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals("Adding 2 positive values in column", 10.0, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
    // Test for positive and negative values 
	@Test
	public void calculateColumnTotalForThreeValuesMixedNegativeAndPositive() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		
		mockingContext.checking(new Expectations() {{
			// values table has 3 rows
			oneOf(values).getRowCount();
			will(returnValue(3));
			// fist value in first column
			oneOf(values).getValue(0, 0);
			will(returnValue(2.0));
			// second value in first column
			oneOf(values).getValue(1, 0);
			will(returnValue(-2.5));
			// third value in first column
			oneOf(values).getValue(2, 0);
			will(returnValue(8.0));
		}});
		
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 0);
		
		// verify
		assertEquals("Adding 3 negative and positive values in column", 7.5, result, .000000001d);
		
		// tear-down: None in this test method

	}
	
	// Test of when Null is given 
	@Test (expected = IllegalArgumentException.class)
	public void calculateColumnTotalNullInput() throws IllegalArgumentException {
		// no setup required
		
		// exercise
		DataUtilities.calculateColumnTotal(null, 0);
		
	}
	// Test with valid column index
	@Test
	public void calculateColumnTotalValidColumnIndexOne() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		
		mockingContext.checking(new Expectations() {{
			// values table has 3 rows
			oneOf(values).getRowCount();
			will(returnValue(3));
			// fist value in second column
			oneOf(values).getValue(0, 1);
			will(returnValue(2.0));
			// second value in second column
			oneOf(values).getValue(1, 1);
			will(returnValue(2.5));
			// third value in second column
			oneOf(values).getValue(2, 1);
			will(returnValue(7.5));
		}});
		
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 1);
		
		// verify
		assertEquals("Testing Valid Column Index", 12.0, result, .000000001d);
		
		// tear-down: None in this test method
	}
	
	
// METHOD #2: calculateRowTotal()	
	
	// Test with 4 positive values 
	@Test
	public void calculateRowTotalForFourPositiveValues() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		
		mockingContext.checking(new Expectations() {{
			// values table has 4 columns
			oneOf(values).getColumnCount();
			will(returnValue(4));
			// fist value in first row
			oneOf(values).getValue(0, 0);
			will(returnValue(2.0));
			// second value in first row
			oneOf(values).getValue(0, 1);
			will(returnValue(2.5));
			// third value in first row
			oneOf(values).getValue(0, 2);
			will(returnValue(7.5));
			// fourth value in first row
			oneOf(values).getValue(0, 3);
			will(returnValue(3.0));
		}});
		
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		// verify
		assertEquals("Adding 4 positive values in row", 15.0, result, .000000001d);
	}
	
	// Test for mixed negative and positive values
	@Test
	public void calculateRowTotalforMixedNegativeAndPositive() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		
		mockingContext.checking(new Expectations() {{
			// values table has 3 columns
			oneOf(values).getColumnCount();
			will(returnValue(3));
			// fist value in first row
			oneOf(values).getValue(0, 0);
			will(returnValue(-100.0));
			// second value in first row
			oneOf(values).getValue(0, 1);
			will(returnValue(300.0));
			// third value in first row
			oneOf(values).getValue(0, 2);
			will(returnValue(500.0));
		}});
		
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		// verify
		assertEquals("Adding positive and negative values in row", 700.0, result, .000000001d);
	}
	
	// Test with null value 
	@Test (expected = IllegalArgumentException.class)
	public void calculateRowTotalNullInput() throws NullPointerException {
		// no setup required
		
		// exercise
		DataUtilities.calculateRowTotal(null, 0);
		
	}
	
	// Test with valid row index
	@Test
	public void calculateRowTotalValidRowIndexOne() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		
		mockingContext.checking(new Expectations() {{
			// values table has 3 columns
			oneOf(values).getColumnCount();
			will(returnValue(3));
			// fist value in second row
			oneOf(values).getValue(1, 0);
			will(returnValue(2.0));
			// second value in second row
			oneOf(values).getValue(1, 1);
			will(returnValue(2.5));
			// third value in second row
			oneOf(values).getValue(1, 2);
			will(returnValue(7.5));
		}});
		
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 1);
		
		// verify
		assertEquals("Testing Valid Column Index", 12.0, result, .000000001d);
		
		// tear-down: None in this test method
	}
	
// METHOD #3: createNumberArray()
	
	// Test of creating an array
	@Test
	public void createNumberArraySimple1x7() {
		// setup
		double[] passedIn = {0.1, 0.2, 0.3, 4.0, 5.0, 6.0, 7.0};
		Number[] expected = {0.1, 0.2, 0.3, 4.0, 5.0, 6.0, 7.0};
		
		// exercise
		Number[] result = DataUtilities.createNumberArray(passedIn);
		
		// verify
		assertArrayEquals(expected, result);
		
		// tear-down
		passedIn = null;
		expected = null;
		result = null;
	}
	// Test of creating empty array
	@Test
	public void createNumberArrayEmpty() {
		// setup
		double[] passedIn = {};
		Number[] expected = {};
		
		// exercise
		Number[] result = DataUtilities.createNumberArray(passedIn);
		
		// verify
		assertArrayEquals(expected, result);
		
		// tear-down
		passedIn = null;
		expected = null;
		result = null;
	}
	
	// Test of creating array with null
	@Test (expected = IllegalArgumentException.class)
	public void createNumberArrayNullInput() throws IllegalArgumentException {
		// setup: None
		
		// exercise
		DataUtilities.createNumberArray(null);
	}
	
// METHOD #4: createNumberArray2D()
	
	// test of creating simple 2D array 3x1
	@Test
	public void createNumberArray2DSimple3x1() {
		// setup
		double[][] passedIn = { {0.1},
								{1},
								{100}
							  };
		Number[][] expected = { {0.1},
								{1.0},
								{100.0}
			  				  };
		
		// exercise
		Number[][] result = DataUtilities.createNumberArray2D(passedIn);
		
		// verify
		assertArrayEquals(expected, result);
		
		// tear-down
		passedIn = null;
		expected = null;
		result = null;
	}
	// Test of creating simple 2D array 3x4
	@Test
	public void createNumberArray2DComplex3x4() {
		// setup
		double[][] passedIn = { {0.1, 0.2, 0.3, 0.4},
								{1, 2, 3, 4},
								{100, 200, 300, 400}
							  };
		Number[][] expected = { {0.1, 0.2, 0.3, 0.4},
								{1.0, 2.0, 3.0, 4.0},
								{100.0, 200.0, 300.0, 400.0}
			  				  };
		
		// exercise
		Number[][] result = DataUtilities.createNumberArray2D(passedIn);
		
		// verify
		assertArrayEquals(expected, result);
		
		// tear-down
		passedIn = null;
		expected = null;
		result = null;
	}
	// Test of creating simple 2D array empty
	@Test
	public void createNumberArray2DEmpty() {
		// setup
		double[][] passedIn = {};
		Number[][] expected = {};
		
		// exercise
		Number[][] result = DataUtilities.createNumberArray2D(passedIn);
		
		// verify
		assertArrayEquals(expected, result);
		
		// tear-down
		passedIn = null;
		expected = null;
		result = null;
	}
	
	// Test of creating simple 2D array with null 
	@Test (expected = IllegalArgumentException.class)
	public void createNumberArray2DNullInput() throws IllegalArgumentException {
		// setup: None
		
		// exercise
		DataUtilities.createNumberArray2D(null);
	}
	
// METHOD #5: getCumulativePercentages()
	// Test for first 3 pairs
	@Test
	public void getCumulativePercentagesThreeFirstPair() {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues kValues = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {{
			// keys
			allowing(kValues).getItemCount();
			will(returnValue(3));
			
			// first key-value pair (0, 10)
			allowing(kValues).getKey(0);
			will(returnValue(0));
			allowing(kValues).getValue(0);
			will(returnValue(10));
			
			// second key-value pair (1, 20)
			allowing(kValues).getKey(1);
			will(returnValue(1));
			allowing(kValues).getValue(1);
			will(returnValue(20));
			
			// third key-value pair (2, 30)
			allowing(kValues).getKey(2);
			will(returnValue(2));
			allowing(kValues).getValue(2);
			will(returnValue(30));
		}});
		
		// exercise
		KeyedValues result = DataUtilities.getCumulativePercentages(kValues);
		
		// verify
		assertEquals("The cumulative percentage at key 0", 10.0 / (10.0 + 20.0 + 30.0), result.getValue(0).doubleValue(), .1d);
		
	}
	// Test for the calculating up to 2nd pair
	@Test
	public void getCumulativePercentagesThreeSecondPair() {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues kValues = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {{
			// keys
			allowing(kValues).getItemCount();
			will(returnValue(3));
			
			// first key-value pair (0, 10)
			allowing(kValues).getKey(0);
			will(returnValue(0));
			allowing(kValues).getValue(0);
			will(returnValue(10));
			
			// second key-value pair (1, 20)
			allowing(kValues).getKey(1);
			will(returnValue(1));
			allowing(kValues).getValue(1);
			will(returnValue(20));
			
			// third key-value pair (2, 30)
			allowing(kValues).getKey(2);
			will(returnValue(2));
			allowing(kValues).getValue(2);
			will(returnValue(30));
		}});
		
		// exercise
		KeyedValues result = DataUtilities.getCumulativePercentages(kValues);
		
		// verify
		assertEquals("The cumulative percentage at key 1", (10.0 + 20.0) / (10.0 + 20.0 + 30.0), result.getValue(1).doubleValue(), .1d);
		
	}
	// Test for the calculating up to 3rd pair
	@Test
	public void getCumulativePercentagesThreeThirdPair() {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues kValues = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {{
			// keys
			allowing(kValues).getItemCount();
			will(returnValue(3));
			
			// first key-value pair (0, 10)
			allowing(kValues).getKey(0);
			will(returnValue(0));
			allowing(kValues).getValue(0);
			will(returnValue(10));
			
			// second key-value pair (1, 20)
			allowing(kValues).getKey(1);
			will(returnValue(1));
			allowing(kValues).getValue(1);
			will(returnValue(20));
			
			// third key-value pair (2, 30)
			allowing(kValues).getKey(2);
			will(returnValue(2));
			allowing(kValues).getValue(2);
			will(returnValue(30));
		}});
		
		// exercise
		KeyedValues result = DataUtilities.getCumulativePercentages(kValues);
		
		// verify
		assertEquals("The cumulative percentage at key 2", (10.0 + 20.0 + 30.0) / (10.0 + 20.0 + 30.0), result.getValue(2).doubleValue(), .1d);
		
	}
	// Test for the calculating with one null
	@Test
	public void getCumulativePercentagesTwoPairsOneNull() {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues kValues = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {{
			// keys
			allowing(kValues).getItemCount();
			will(returnValue(2));
			
			// first key-value pair (0, 100)
			allowing(kValues).getKey(0);
			will(returnValue(0));
			allowing(kValues).getValue(0);
			will(returnValue(100));
			
			// second key-value pair (1, null)
			allowing(kValues).getKey(1);
			will(returnValue(1));
			allowing(kValues).getValue(1);
			will(returnValue(null));
			
		}});
		
		// exercise
		KeyedValues result = DataUtilities.getCumulativePercentages(kValues);
		
		// verify
		assertEquals("The cumulative percentage at key 0", 1.0, result.getValue(1).doubleValue(), .1d);
		
	}
	// Test for the calculating with one negative 
	@Test
	public void getCumulativePercentagesThreePairsOneNegative() {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues kValues = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {{
			// keys
			allowing(kValues).getItemCount();
			will(returnValue(3));
			
			// first key-value pair (0, 10)
			allowing(kValues).getKey(0);
			will(returnValue(0));
			allowing(kValues).getValue(0);
			will(returnValue(10));
			
			// second key-value pair (1, -200)
			allowing(kValues).getKey(1);
			will(returnValue(1));
			allowing(kValues).getValue(1);
			will(returnValue(-200));
			
			// second key-value pair (2, 30)
			allowing(kValues).getKey(2);
			will(returnValue(2));
			allowing(kValues).getValue(2);
			will(returnValue(30));
			
		}});
		
		// exercise
		KeyedValues result = DataUtilities.getCumulativePercentages(kValues);
		
		// verify
		assertEquals("The cumulative percentage at key 1 (negative value)", (10 - 200) / (10.0 - 200.0 + 30.0), result.getValue(1).doubleValue(), .1d);
		
	}
	// Test for the calculating with one null
	@Test (expected = IllegalArgumentException.class)
	public void getCumulativePercentagesNullInput() throws IllegalArgumentException {
		// setup: None
		
		// exercise
		DataUtilities.getCumulativePercentages(null);
	}
	
// More Test Cases Added -----------------------------------------------------------

// Test cases for equal()
	
	// Test with two nulls
	@Test
    public void testBothArraysNull() {
        // Tests the scenario where both input arrays are null. According to the method definition,
        // two null arrays are considered equal, so the test expects a true result.
        assertTrue("Both arrays are null", DataUtilities.equal(null, null));
    }
	
	// Test with first array being null
    @Test
    public void testFirstArrayNullSecondArrayNotNull() {
        // Tests the case where the first input array is null and the second is not null.
        // These arrays should not be considered equal, so the test expects a false result.
        double[][] secondArray = new double[][]{{1.0, 2.0}};
        assertFalse("First array is null, second array is not null", DataUtilities.equal(null, secondArray));
    }
    // Test with second array being null
    @Test
    public void testFirstArrayNotNullSecondArrayNull() {
        // Tests the case where the first input array is not null and the second array is null.
        // These conditions should result in the arrays not being considered equal, expecting a false result.
        double[][] firstArray = new double[][]{{1.0, 2.0}};
        assertFalse("First array is not null, second array is null", DataUtilities.equal(firstArray, null));
    }
    // Test with empty arrays
    @Test
    public void testBothArraysEmpty() {
        // Tests the scenario where both input arrays are empty. Empty arrays should be considered
        // equal, so the test expects a true result.
        double[][] firstArray = new double[][]{};
        double[][] secondArray = new double[][]{};
        assertTrue("Both arrays are empty", DataUtilities.equal(firstArray, secondArray));
    }
    // Test with different lengths of arrays
    @Test
    public void testArraysDifferentLengths() {
        // Tests the scenario where the input arrays are of different lengths.
        // Arrays of different lengths cannot be equal, so the test expects a false result.
        double[][] firstArray = new double[][]{{1.0, 2.0}};
        double[][] secondArray = new double[][]{};
        assertFalse("Arrays have different lengths", DataUtilities.equal(firstArray, secondArray));
    }
    // Test with same length but different elements 
    @Test
    public void testArraysSameLengthDifferentElements() {
        // Tests the scenario where the input arrays are of the same length but contain different elements.
        // Such arrays should not be considered equal, so the test expects a false result.
        double[][] firstArray = new double[][]{{1.0, 2.0}};
        double[][] secondArray = new double[][]{{1.0, 3.0}};
        assertFalse("Arrays have same length but different elements", DataUtilities.equal(firstArray, secondArray));
    }
    // Test with with valid arrays
    @Test
    public void testEqualNonEmptyArrays() {
        // Tests the scenario where the input arrays are non-empty and contain exactly the same elements.
        // The test expects a true result, indicating that the arrays are equal.
        double[][] firstArray = new double[][]{{1.0, 2.0}, {3.0, 4.0}};
        double[][] secondArray = new double[][]{{1.0, 2.0}, {3.0, 4.0}};
        assertTrue("Both arrays are equal and non-empty", DataUtilities.equal(firstArray, secondArray));
    }
    // Test with positive infinity and NaN
    @Test
    public void testArraysSpecialValues() {
        // Tests the scenario where the input arrays contain special floating-point values (NaN, infinity),
        // which should be treated correctly by the equality check. The test expects a true result,
        // indicating that the arrays are considered equal.
        double[][] firstArray = new double[][]{{Double.NaN, Double.POSITIVE_INFINITY}};
        double[][] secondArray = new double[][]{{Double.NaN, Double.POSITIVE_INFINITY}};
        assertTrue("Arrays contain special values and are considered equal", DataUtilities.equal(firstArray, secondArray));
    }
    
// Test of the clone Method
    // Testing non empty arrays
    @Test
    public void testCloneNonEmptyArray() {
        // Verifies that a non-empty 2D array is cloned correctly, with all values preserved and the clone being a different object.
        double[][] source = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] cloned = DataUtilities.clone(source);
        assertNotSame("Cloned array should be a different object", source, cloned);
        assertArrayEquals("Cloned array should be equal to the source array", source, cloned);
    }
    // Testing empty arrays
    @Test
    public void testCloneEmptyArray() {
        // Tests cloning an empty 2D array to ensure the method returns a new empty array, not the same instance.
        double[][] source = new double[0][0];
        double[][] cloned = DataUtilities.clone(source);
        assertNotSame("Cloned array should be a different object", source, cloned);
        assertEquals("Cloned array should be empty", 0, cloned.length);
    }
    // Testing with one array having a null row
    @Test
    public void testCloneArrayWithNullRows() {
        // Checks the method's ability to clone a 2D array that contains `null` rows, ensuring `null` rows are preserved in the clone.
        double[][] source = {{1.0, 2.0}, null, {3.0, 4.0}};
        double[][] cloned = DataUtilities.clone(source);
        assertNotSame("Cloned array should be a different object", source, cloned);
        assertNull("Cloned array should preserve null rows", cloned[1]);
    }
    // Testing if the clone method can handle different row lengths
    @Test
    public void testCloneArrayWithVaryingRowLengths() {
        // Ensures that the method can clone a 2D array with rows of varying lengths accurately.
        double[][] source = {{1.0}, {2.0, 3.0}};
        double[][] cloned = DataUtilities.clone(source);
        assertNotSame("Cloned array should be a different object", source, cloned);
        assertArrayEquals("Cloned array should be equal to the source array", source, cloned);
    }
    // Testing clone with null value
    @Test(expected = IllegalArgumentException.class)
    public void testCloneNullArray() {
        // Confirms that attempting to clone a null 2D array throws an IllegalArgumentException.
        DataUtilities.clone(null);
    }
// Testing of calculateColumnTotal
    
    // Test with valid rows
    @Test
    public void calculateColumnTotalWithValidRows() {
        // Setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        final int[] validRows = {0, 2}; // Test with specific valid rows
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(3)); // 3 rows
            oneOf(values).getValue(0, 1); will(returnValue(5)); // Value at valid row 0
            oneOf(values).getValue(2, 1); will(returnValue(10)); // Value at valid row 2
            // Note: Row 1 is skipped because it's not listed in validRows
        }});

        // Exercise
        double result = DataUtilities.calculateColumnTotal(values, 1, validRows);

        // Verify
        assertEquals("Total of specified rows in column", 15.0, result, .000000001d);

        // Tear-down: NONE in this test method
    }
    // Test with no valid rows
    @Test
    public void calculateColumnTotalWithNoValidRows() {
        // Setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        final int[] validRows = {}; // No valid rows
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(3)); // 3 rows but none are valid
            // No values are fetched because no rows are valid
        }});

        // Exercise
        double result = DataUtilities.calculateColumnTotal(values, 1, validRows);

        // Verify
        assertEquals("Total of no rows should be 0", 0.0, result, .000000001d);

        // Tear-down: NONE in this test method
    }
    // Test with null data
    @Test(expected = IllegalArgumentException.class)
    public void calculateColumnTotalWithNullData() {
        // Setup is not required as the method should throw an exception
        
        // Exercise
        DataUtilities.calculateColumnTotal(null, 0, new int[]{0, 1}); // Passing null as the data parameter

        // Verify is handled by the expected exception
    }
    // Test with a row index outside of the number of rows
    @Test
    public void calculateColumnTotalWithInvalidRowIndices() {
        // Setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        final int[] validRows = {3, 4}; // Indices outside the actual row count
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(3)); // Only 3 rows exist
            // No values are fetched because the validRows indices are out of bounds
        }});

        // Exercise
        double result = DataUtilities.calculateColumnTotal(values, 1, validRows);

        // Verify
        assertEquals("Total for out-of-bounds rows should be 0", 0.0, result, .000000001d);

        // Tear-down: NONE in this test method
    }
    // Test with some null values
    @Test
    public void calculateColumnTotalWithSomeNullValues() {
        // Setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        final int[] validRows = {0, 1, 2}; // Considering all rows
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(3));
            oneOf(values).getValue(0, 1); will(returnValue(5)); // Valid value
            oneOf(values).getValue(1, 1); will(returnValue(null)); // Null value should be skipped
            oneOf(values).getValue(2, 1); will(returnValue(10)); // Valid value
        }});

        // Exercise
        double result = DataUtilities.calculateColumnTotal(values, 1, validRows);

        // Verify
        assertEquals("Total should only include non-null values", 15.0, result, .000000001d);

        // Tear-down: NONE in this test methods
    }
    // Test with a valid col selection 
    @Test
    public void calculateRowTotalWithValidCols() {
        // Setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        final int row = 1;
        final int[] validCols = {0, 2}; // Only columns 0 and 2 are valid
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount(); will(returnValue(3));
            oneOf(values).getValue(row, 0); will(returnValue(5));
            oneOf(values).getValue(row, 2); will(returnValue(10));
        }});

        // Exercise
        double result = DataUtilities.calculateRowTotal(values, row, validCols);

        // Verify
        assertEquals("The total should be correct for specified valid columns", 15.0, result, 0.0000001d);
    }
    // Test with an invalid column selection
    @Test
    public void calculateRowTotalWithNoValidCols() {
        // Setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        final int row = 1;
        final int[] validCols = {}; // No valid columns
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount(); will(returnValue(3)); // Column count is not really relevant in this case
        }});

        // Exercise
        double result = DataUtilities.calculateRowTotal(values, row, validCols);

        // Verify
        assertEquals("The total should be 0 when no valid columns are specified", 0.0, result, 0.0000001d);
    }
	
}
