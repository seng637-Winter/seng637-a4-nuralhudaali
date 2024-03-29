package org.jfree.data.test;
import java.lang.reflect.Field;
import org.junit.*;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.Range;
import org.junit.BeforeClass;
import org.junit.Test;
import java.lang.reflect.Method;
public class RangeTest {
	private Range exampleRange;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		exampleRange = new Range(-1, 1);
	}
	
	// Example test from assignment instructions
	@Test
	public void centralValueShouldBeZero() {
		assertEquals("The central value of -1 and 1 should be 0", 0, exampleRange.getCentralValue(), 0.000000001d);
	}
	// METHOD #1: getLowerBound()
	
	@Test
	public void constrainValueBelowLowerLimit() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = -1000;
		
		// exercise
		double result = exampleRange.constrain(value);
		
		// verify
		assertEquals("Constraining value below lower limit of range (-1000), result should be lower limit -1:", -1, result, .000000001d);
		
		// tear-down: none in this method
	}
	
	@Test
	public void constrainValueAboveUpperLimit() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = 1000;
		
		// exercise
		double result = exampleRange.constrain(value);
		
		// verify
		assertEquals("Constraining value above upper limit of range (1000), result should be upper limit 1:", 1, result, .000000001d);
		
		// tear-down: non in this method
	}
	
	@Test
	public void constrainValueWithinRange() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = -0.5;
		
		// exercise
		double result = exampleRange.constrain(value);
		
		// verify
		assertEquals("Constraining value within the range (-0.5), result should be the same value -0.5:", -0.5, result, .000000001d);
		
		// tear-down
	}
	
	@Test
	public void constrainInvalidValue() throws Exception {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = Double.NaN;
		
		// exercise
		exampleRange.constrain(value);
		
	}
	
	
	// METHOD #2: expandToInclude()
	
	@Test
	public void expandToIncludeValueAboveUpperLimit() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = 200;
		Range expected = new Range(-1, 200); // upper limit should increase
		
		// exercise
		Range result = Range.expandToInclude(exampleRange, value);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void expandToIncludeValueBelowLowerLimit() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = -200;
		Range expected = new Range(-200, 1); // lower limit should decrease
		
		// exercise
		Range result = Range.expandToInclude(exampleRange, value);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void expandToIncludeValueWithinRange() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = 0;
		Range expected = new Range(-1, 1); // no change should occur
		
		// exercise
		Range result = Range.expandToInclude(exampleRange, value);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void expandToIncludeInvalidValue() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = Double.NaN;
		
		// exercise
	    Range expandedRange = Range.expandToInclude(exampleRange, value);
	    
	    // verify the expanded range - should be the same as the input range
	    assertEquals(exampleRange, expandedRange);
	}
	
	
	// METHOD #3: shift()
	
	@Test
	public void shiftAcrossZeroByPositiveAmount() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double shiftAmount = 5;
		boolean allowZeroCrossing = true;
		
		Range expected = new Range(4, 6);
		
		// exercise
		Range result = Range.shift(exampleRange, shiftAmount, allowZeroCrossing);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void shiftAcrossZeroByNegativeAmount() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double shiftAmount = -5;
		boolean allowZeroCrossing = true;
		
		Range expected = new Range(-6, -4);
		
		// exercise
		Range result = Range.shift(exampleRange, shiftAmount, allowZeroCrossing);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void shiftNotAcrossZeroByPositiveAmount() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double shiftAmount = 5;
		boolean allowZeroCrossing = false; // no zero crossing
		
		Range expected = new Range(0, 6);
		
		// exercise
		Range result = Range.shift(exampleRange, shiftAmount, allowZeroCrossing);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void shiftNotAcrossZeroByNegativeAmount() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double shiftAmount = -5;
		boolean allowZeroCrossing = false; // no zero crossing
		
		Range expected = new Range(-6, 0);
		
		// exercise
		Range result = Range.shift(exampleRange, shiftAmount, allowZeroCrossing);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void shiftByInvalidAmonut() throws Exception {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double shiftAmount = Double.NaN;
		boolean allowZeroCrossing = false; // either false or true, does not matter for this test case
		
		// exercise
		Range.shift(exampleRange, shiftAmount, allowZeroCrossing);
		
	}
	
	
	// METHOD #4: contains()
	
	@Test
	public void containsValueSlightlyAboveRange() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = 1.01;
		
		// exercise
		boolean result = exampleRange.contains(value);
		
		// verify
		assertFalse(result);
	}
	
	@Test
	public void containsValueSlightlyBelowRange() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = -1.01;
		
		// exercise
		boolean result = exampleRange.contains(value);
		
		// verify
		assertFalse(result);
	}
	
	@Test
	public void containsValueWithinRange() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = -0.5;
		
		// exercise
		boolean result = exampleRange.contains(value);
		
		// verify
		assertTrue(result);
	}
	
	@Test
	public void containsInvalidValue() throws Exception {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = Double.NaN;
		
		// exercise
		exampleRange.contains(value);
	}
	
	
	// METHOD #5: combine()
	
	@Test
	public void combineOverlappingRanges() {
		// setup
		Range range1 = new Range(-20, 1);
		Range range2 = new Range(-5, 50);
		Range expected = new Range(-20, 50);
		
		// exercise
		Range result = Range.combine(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		range1 = null;
		range2 = null;
		expected = null;
		result = null;
	}
	
	@Test
	public void combineDistinctRanges() {
		// setup
		Range range1 = new Range(-20, 1);
		Range range2 = new Range(50, 100);
		Range expected = new Range(-20, 100);
		
		// exercise
		Range result = Range.combine(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		range1 = null;
		range2 = null;
		expected = null;
		result = null;
	}

// New Test cases --------------------------------------------------------------
    
	// Added coverage to Range.contains()
	@Test
    public void containsShouldReturnTrueForValueEqualToLowerAndUpperBound() {
        // Set up a range where the lower and upper bounds are the same
        double bound = 5;
        Range singlePointRange = new Range(bound, bound);
        
        // Check if the range correctly reports that it contains the value
        // that is equal to both the lower and upper bounds
        assertTrue("Range should contain the value equal to its lower and upper bound", 
                   singlePointRange.contains(bound));
    }
    
	// Intersects Method 
	
	// Test case 1: External range is completely before the example range
    @Test
    public void intersectsShouldReturnFalseWhenRangeIsCompletelyBefore() {
    	// @before exampleRange [-1,1]
        assertFalse("Range before example range should not intersect", exampleRange.intersects(-3, -2));
    }

    // Test case 2: External range starts before the example range but overlaps its lower bound
    @Test
    public void intersectsShouldReturnTrueWhenRangeOverlapsLowerBound() {
    	// @before exampleRange [-1,1]
        assertTrue("Range overlapping lower bound should intersect", exampleRange.intersects(-2, 0));
    }

    // Test case 3: External range is entirely within the example range
    @Test
    public void intersectsShouldReturnTrueWhenRangeIsEntirelyWithin() {
    	// @before exampleRange [-1,1]
        assertTrue("Range entirely within example range should intersect", exampleRange.intersects(-0.5, 0.5));
    }

    // Test case 4: External range starts within the example range and extends beyond its upper bound
    @Test
    public void intersectsShouldReturnTrueWhenRangeExtendsBeyondUpperBound() {
    	// @before exampleRange [-1,1]
        assertTrue("Range extending beyond upper bound should intersect", exampleRange.intersects(0, 2));
    }

    // Test case 5: External range is completely after the example range
    @Test
    public void intersectsShouldReturnFalseWhenRangeIsCompletelyAfter() {
    	// @before exampleRange [-1,1]
        assertFalse("Range after example range should not intersect", exampleRange.intersects(2, 3));
    }

    // Test case 6: External range's lower bound (`b0`) is exactly the example range's upper bound
    @Test
    public void intersectsShouldReturnFalseWhenRangeLowerBoundIsExactlyAtUpperBound() {
    	// @before exampleRange [-1,1]
    	assertFalse("Range starting at example range's upper bound should not intersect", exampleRange.intersects(1, 2));
    }
    @Test
    public void beforeRangeDoesNotIntersect() {
        Range beforeRange = new Range(-3, -2);
        assertFalse(exampleRange.intersects(beforeRange));
    }
    
    
    // testing when Nan range, should return a range of the value
    public void testExpandToIncludeIntoNaNRange() {
        // Create a range with NaN bounds
        Range nanRange = new Range(Double.NaN, Double.NaN);
        double valueToInclude = 5.0;
        Range expected = new Range(valueToInclude,valueToInclude);

        // Attempt to expand this NaN range to include a normal value
        assertEquals("The range returned was correct", expected, Range.expandToInclude(nanRange, valueToInclude));
    }
    
    // Test expanding with positive margins
    @Test
    public void expandWithPositiveMargins() {
        Range originalRange = new Range(0, 100);
        double lowerMargin = 0.1; // 10% of the length
        double upperMargin = 0.1; // 10% of the length
        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);
        
        assertEquals("Lower bound should be decreased by 10 units", -10, expandedRange.getLowerBound(), 0.000001);
        assertEquals("Upper bound should be increased by 10 units", 110, expandedRange.getUpperBound(), 0.000001);
    }

    // Test expanding with negative margins
    @Test
    public void expandWithNegativeMargins() {
        Range originalRange = new Range(0, 100);
        double lowerMargin = -0.1; // -10% of the length
        double upperMargin = -0.1; // -10% of the length
        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);
        
        assertEquals("Lower bound should be increased by 10 units", 10, expandedRange.getLowerBound(), 0.000001);
        assertEquals("Upper bound should be decreased by 10 units", 90, expandedRange.getUpperBound(), 0.000001);
    }

    // Test expanding with zero margins
    @Test
    public void expandWithZeroMargins() {
        Range originalRange = new Range(0, 100);
        double lowerMargin = 0; // No expansion
        double upperMargin = 0; // No expansion
        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);
        
        assertEquals("Lower bound should remain unchanged", 0, expandedRange.getLowerBound(), 0.000001);
        assertEquals("Upper bound should remain unchanged", 100, expandedRange.getUpperBound(), 0.000001);
    }
    
 // Test scaling with a positive factor
    @Test
    public void scaleWithPositiveFactor() {
        Range originalRange = new Range(10, 20);
        double factor = 2; // Scaling factor
        Range scaledRange = Range.scale(originalRange, factor);
        
        assertEquals("Lower bound should be scaled correctly", 20, scaledRange.getLowerBound(), 0.000001);
        assertEquals("Upper bound should be scaled correctly", 40, scaledRange.getUpperBound(), 0.000001);
    }

    // Test scaling with zero factor
    @Test
    public void scaleWithZeroFactor() {
        Range originalRange = new Range(10, 20);
        double factor = 0; // Scaling factor
        Range scaledRange = Range.scale(originalRange, factor);
        
        assertEquals("Lower bound should be zero", 0, scaledRange.getLowerBound(), 0.000001);
        assertEquals("Upper bound should be zero", 0, scaledRange.getUpperBound(), 0.000001);
    }

    // Test scaling with a negative factor (expected to throw an exception)
    @Test(expected = IllegalArgumentException.class)
    public void scaleWithNegativeFactorShouldThrowException() {
        Range originalRange = new Range(10, 20);
        double factor = -1; // Scaling factor
        Range.scale(originalRange, factor); // This should throw an IllegalArgumentException
    }

    // Test scaling with a factor less than 1 (but positive), which reduces the range
    @Test
    public void scaleWithFactorLessThanOne() {
        Range originalRange = new Range(10, 20);
        double factor = 0.5; // Scaling factor
        Range scaledRange = Range.scale(originalRange, factor);
        
        assertEquals("Lower bound should be scaled down correctly", 5, scaledRange.getLowerBound(), 0.000001);
        assertEquals("Upper bound should be scaled down correctly", 10, scaledRange.getUpperBound(), 0.000001);
    }
    
    // Range.hashcode()
    
    // Test hashCode consistency for the same object
    @Test
    public void hashCodeConsistencyForSameObject() {
        Range range = new Range(1.0, 5.0);
        int hashCode1 = range.hashCode();
        int hashCode2 = range.hashCode();
        
        assertEquals("Hash code should be consistent for the same object", hashCode1, hashCode2);
    }

    // Test hashCode equality for two equal Range objects
    @Test
    public void hashCodeEqualityForEqualObjects() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(1.0, 5.0); // Equal to range1
        
        assertEquals("Equal objects must have equal hash codes", range1.hashCode(), range2.hashCode());
    }

    // Test hashCode difference for two non-equal Range objects
    @Test
    public void hashCodeDifferenceForNonEqualObjects() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(2.0, 6.0); // Not equal to range1
        
        // Use assertTrue to check that the hash codes are not equal
        assertTrue("Non-equal objects should (ideally) have different hash codes", range1.hashCode() != range2.hashCode());
    }
    
    // Testing of toString()
    
    // Test that should return the exampleRange
    @Test
    public void toStringReturnsExpectedFormat() {
        // @Before exampleRange [-1, 1]
        
        // Define the expected String format
        String expected = "Range[-1.0,1.0]";
        
        // Assert that the Range's toString method returns the expected String
        assertEquals("toString method should return the correct format", expected, exampleRange.toString());
    }
    
    // Test to cover the shift method
    @Test
    public void shiftShouldDelegateCorrectly() {
        Range base = new Range(10, 20);
        double delta = 5;
        Range expected = new Range(15, 25); // Expected result after shifting

        Range result = Range.shift(base, delta);

        assertEquals("The shifted range's lower bound is not as expected", expected.getLowerBound(), result.getLowerBound(), 0.000001);
        assertEquals("The shifted range's upper bound is not as expected", expected.getUpperBound(), result.getUpperBound(), 0.000001);
    }
    
    
    // Test for the equal method
    
    //Test for when the equals is not a Range object
    @Test
    public void equalsShouldReturnFalseForNull() {
        Range range = new Range(1, 5);
        double x = 1.0;
        
        assertFalse("Equals should return false for null", range.equals(x));
    }

    // Test case where obj is a Range with same lower and upper bounds
    @Test
    public void equalsShouldReturnTrueForSameRange() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(1, 5);
        
        assertTrue("Equals should return true for Ranges with same bounds", range1.equals(range2));
    }

    // Test case where obj is a Range with different lower bounds
    @Test
    public void equalsShouldReturnFalseForDifferentLowerBounds() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(2, 5);
        
        assertFalse("Equals should return false for Ranges with different lower bounds", range1.equals(range2));
    }

    // Test case where obj is a Range with different upper bounds
    @Test
    public void equalsShouldReturnFalseForDifferentUpperBounds() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(1, 6);
        
        assertFalse("Equals should return false for Ranges with different upper bounds", range1.equals(range2));
    }

    // Test case where obj is the same object as 'this'
    @Test
    public void equalsShouldReturnTrueForSameObject() {
        Range range = new Range(1, 5);
        
        assertTrue("Equals should return true for the same object", range.equals(range));
    }
    
    
    // Method Combine
    
    // Testing of combine, using range1 as a null 
	@Test
	public void combineNullRangeAndNonNullRange() {
		// setup
		Range range1 = null;
		Range range2 = new Range(-5, 50);
		Range expected = new Range(-5, 50);
		
		// exercise
		Range result = Range.combine(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		range2 = null;
		expected = null;
		result = null;
	}
	// Combine two null ranges
	@Test
	public void combineTwoNullRanges() {
		// setup
		Range range1 = null;
		Range range2 = null;
		Range expected = null;
		
		// exercise
		Range result = Range.combine(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		result = null;
	}
	// Combine testing using the 2nd range as null
	@Test
	public void combineRange2NullRanges() {
		// setup
		Range range1 = new Range(1,2);
		Range range2 = null;
		Range expected = new Range(1,2);
		
		// exercise
		Range result = Range.combine(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		result = null;
	}
	
	
// Testing of the combineIgnoringNaN
	
	// Test Both Null ranges
	@Test
	public void combineIgnoringNaNTwoNullRanges() {
		// setup
		Range range1 = null;
		Range range2 = null;
		Range expected = null;
		
		// exercise
		Range result = Range.combineIgnoringNaN(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		result = null;
	}
	// Test combineIgnoringNaN using range1 as null
	@Test
	public void combineIgnoringNaNFirstNullRanges() {
		// setup
		Range range1 = null;
		Range range2 = new Range(1,2);
		Range expected = new Range(1,2);
		
		// exercise
		Range result = Range.combineIgnoringNaN(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		result = null;
	}
	// Test combineIgnoringNaN using range 2 as null
	@Test
	public void combineIgnoringNaNSecondNullRanges() {
		// setup
		Range range1 = new Range(1,2);
		Range range2 = null;
		Range expected = new Range(1,2);
		
		// exercise
		Range result = Range.combineIgnoringNaN(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		result = null;
	}
	// Test combineIgnoringNaN using valid ranges
	@Test
	public void combineIgnoringNaNWithValidRanges() {
		// setup
		Range range1 = new Range (1,2);
		Range range2 = new Range (1,2);
		Range expected = new Range (1,2);
		
		// exercise
		Range result = Range.combineIgnoringNaN(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		result = null;
	}
	
	
	
// Test for Range constructor with lower bound greater than upper bound
	@Test(expected = IllegalArgumentException.class)
	public void constructorWithLowerBoundGreaterThanUpperShouldThrowException()throws IllegalArgumentException {
	    double lower = 2;
	    double upper = 1;
	    new Range(lower, upper);
	}
// Test for contains method
	
	// Test for value below lower bound
	@Test
	public void containsShouldReturnFalseForValueBelowLowerBound() {
	    Range range = new Range(-5, 5);
	    assertFalse("contains should return false for a value below the lower bound", range.contains(-10));
	}
	// Test for at lower bound
	@Test
	public void containsShouldReturnTrueForValueAtLowerBound() {
	    Range range = new Range(-5, 5);
	    assertTrue("contains should return true for a value exactly at the lower bound", range.contains(-5));
	}
	// Test for within bounds
	@Test
	public void containsShouldReturnTrueForValueWithinRange() {
	    Range range = new Range(-5, 5);
	    assertTrue("contains should return true for a value within the range", range.contains(0));
	}
	// Test at upper bound
	@Test
	public void containsShouldReturnTrueForValueAtUpperBound() {
	    Range range = new Range(-5, 5);
	    assertTrue("contains should return true for a value exactly at the upper bound", range.contains(5));
	}
	// Test for above upper bound
	@Test
	public void containsShouldReturnFalseForValueAboveUpperBound() {
	    Range range = new Range(-5, 5);
	    assertFalse("contains should return false for a value above the upper bound", range.contains(10));
	}
    
// Test of the shift method for code coverage
    @Test
    public void testShiftWhenValueIsZero() throws Exception {
        // Prepare to call the private method
        Method method = Range.class.getDeclaredMethod("shiftWithNoZeroCrossing", double.class, double.class);
        method.setAccessible(true);

        // Define test parameters
        double value = 0.0;
        double delta = 5.0; // Example delta

        // Invoke the private method
        double result = (Double) method.invoke(null, value, delta);

        // Assert the result is as expected
        assertEquals("Shifting 0 by delta should result in delta", delta, result, 0.000001);
    }

	@After
	public void tearDown() throws Exception {
		exampleRange = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

}
